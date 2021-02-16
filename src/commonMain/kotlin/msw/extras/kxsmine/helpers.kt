package msw.extras.kxsmine

import msw.extras.kxsmine.tree.NBTDecodingException
import msw.extras.kxsmine.tree.SNBTDecodingException
import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.payload.ByteArrayPayloadDecoder
import msw.extras.kxsmine.tree.decoding.payload.BytePayloadDecoder
import msw.extras.kxsmine.tree.decoding.payload.CompoundPayloadDecoder
import msw.extras.kxsmine.tree.decoding.payload.DoublePayloadDecoder
import msw.extras.kxsmine.tree.decoding.payload.FloatPayloadDecoder
import msw.extras.kxsmine.tree.decoding.payload.IntArrayPayloadDecoder
import msw.extras.kxsmine.tree.decoding.payload.IntPayloadDecoder
import msw.extras.kxsmine.tree.decoding.payload.ListPayloadDecoder
import msw.extras.kxsmine.tree.decoding.payload.LongArrayPayloadDecoder
import msw.extras.kxsmine.tree.decoding.payload.LongPayloadDecoder
import msw.extras.kxsmine.tree.decoding.payload.ShortPayloadDecoder
import msw.extras.kxsmine.tree.decoding.payload.StringPayloadDecoder
import msw.extras.kxsmine.tree.decoding.tag.TagDecoder
import msw.extras.kxsmine.tree.node.payload.ByteArrayPayloadNode
import msw.extras.kxsmine.tree.node.payload.BytePayloadNode
import msw.extras.kxsmine.tree.node.payload.CompoundPayloadNode
import msw.extras.kxsmine.tree.node.payload.DoublePayloadNode
import msw.extras.kxsmine.tree.node.payload.EndPayloadNode
import msw.extras.kxsmine.tree.node.payload.FloatPayloadNode
import msw.extras.kxsmine.tree.node.payload.IntArrayPayloadNode
import msw.extras.kxsmine.tree.node.payload.IntPayloadNode
import msw.extras.kxsmine.tree.node.payload.ListPayloadNode
import msw.extras.kxsmine.tree.node.payload.LongArrayPayloadNode
import msw.extras.kxsmine.tree.node.payload.LongPayloadNode
import msw.extras.kxsmine.tree.node.payload.PayloadNode
import msw.extras.kxsmine.tree.node.payload.ShortPayloadNode
import msw.extras.kxsmine.tree.node.payload.StringPayloadNode

internal val HEX = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')

internal fun ByteArray.toHexString(offset: Int = 0): String {
    val hexChars = CharArray(size * 2)
    for (i in indices) {
        val v = this[i + offset].toInt() and 0xFF
        hexChars[i * 2] = HEX[v ushr 4]
        hexChars[i * 2 + 1] = HEX[v and 0x0F]
    }
    return hexChars.joinToString("")
}

internal fun ByteArray.chunked(size: Int, offset: Int = 0, until: Int = this.size): List<ByteArray> {
    val thisSize = until - offset
    val resultCapacity = thisSize / size
    val result = ArrayList<ByteArray>(resultCapacity)
    for (i in 0 until resultCapacity) {
        result.add(sliceArray((offset + i * size) until (offset + (i + 1) * size)))
    }
    val remaining = thisSize % size
    if (remaining > 0) {
        val last = ByteArray(size)
        result.add(copyInto(last, startIndex = until - remaining, endIndex = until))
    }

    return result
}

internal fun UShort.msb(): Byte = (this.toInt() ushr 8).toByte()
internal fun UShort.lsb(): Byte = toByte()

internal fun Int.toByteArray(): ByteArray {
    return byteArrayOf(
        (this ushr 24).toByte(),
        (this ushr 16).toByte(),
        (this ushr 8).toByte(),
        toByte()
    )
}

internal fun Long.toByteArray(): ByteArray {
    return byteArrayOf(
        (this ushr 56).toByte(),
        (this ushr 48).toByte(),
        (this ushr 40).toByte(),
        (this ushr 32).toByte(),
        (this ushr 24).toByte(),
        (this ushr 16).toByte(),
        (this ushr 8).toByte(),
        toByte()
    )
}

internal fun String.replaceMultiple(map: Map<String, String>, ignoreCase: Boolean = false): String {
    var ret = this
    for ((old, new) in map) {
        ret = ret.replace(old, new, ignoreCase)
    }
    return ret
}

internal fun String.escape(vararg charsToEscape: Char, ignoreCase: Boolean = false): String {
    return replaceMultiple(charsToEscape.associate { it.toString() to "\\" + it }, ignoreCase)
}

internal fun String.unescape(vararg charsToUnescape: Char, ignoreCase: Boolean = false): String {
    return replaceMultiple(charsToUnescape.associate { "\\" + it to it.toString() }, ignoreCase)
}

internal fun String.truncate(maxLen: Int, replacement: String = "..."): String {
    val actualMaxLen = maxLen - replacement.length
    require(actualMaxLen >= 0) { "The truncation replacement has to be smaller than the length bound" }
    return if (length <= actualMaxLen) this else substring(0..actualMaxLen) + replacement
}

internal fun String.indexOfFirst(offset: Int, until: Int = lastIndex, predicate: (Char) -> Boolean): Int {
    for (index in offset..until) {
        if (predicate(this[index])) {
            return index
        }
    }
    return -1
}

internal fun String.indexOfLast(offset: Int, until: Int = lastIndex, predicate: (Char) -> Boolean): Int {
    for (index in (offset..until).reversed()) {
        if (predicate(this[index])) {
            return index
        }
    }
    return -1
}

internal fun String.indexOfExcluding(
    char: Char,
    exclusionPrefix: String,
    startIndex: Int = 0,
    ignoreCase: Boolean = false,
    ignorePrefixCase: Boolean = false
): Int {
    var exclusionProgress = 0
    var skipNext = false
    for (index in startIndex.coerceAtLeast(0)..lastIndex) {
        if (skipNext) continue

        val current = get(index)

        if (exclusionPrefix[exclusionProgress].equals(current, ignorePrefixCase)) {
            if (++exclusionProgress >= exclusionPrefix.length) {
                skipNext = true
                exclusionProgress = 0
            }
        } else {
            exclusionProgress = 0
        }

        if (current.equals(char, ignoreCase)) return index
    }

    return -1
}

private fun matching(symbol: Char): Char = when (symbol) {
    '(' -> ')'
    '[' -> ']'
    '{' -> '}'
    '<' -> '>'
    else -> throw IllegalArgumentException("Character '$symbol' has no matching counterpart")
}

internal fun String.indexOfMatching(symbol: Char, offset: Int = 0, matching: Char = matching(symbol)): Pair<Int, Int> {
    val stack = mutableListOf<Unit>()
    var first = -1
    for (i in offset..this.length) {
        if (get(i) == symbol) {
            if (first == -1) first = i
            stack.add(Unit)
        }
        if (get(i) == matching) stack.removeLast()
        if (first != -1 && stack.isEmpty()) return first to i
    }
    return first to -1
}

internal fun String.splitTopLevel(startIndex: Int = 0, endIndex: Int = lastIndex, trim: Boolean = false): List<String> {
    val offsetInternal = if (trim) {
        indexOfFirst(startIndex.coerceAtLeast(0), endIndex.coerceAtMost(lastIndex)) { !it.isWhitespace() }
    } else {
        startIndex.coerceAtLeast(0)
    }
    val endInternal = if (trim) {
        indexOfLast(startIndex.coerceAtLeast(0), endIndex.coerceAtMost(lastIndex)) { !it.isWhitespace() }
    } else {
        endIndex.coerceAtMost(lastIndex)
    }
    val result = mutableListOf<String>()
    val accumulator = StringBuilder()

    var i = offsetInternal
    while (i <= endInternal) {
        val cur = get(i)

        val prev = i
        when (cur) {
            '{', '[' -> i = indexOfMatching(cur, i).second
            '\"', '\'' -> i = indexOfExcluding(cur, "\\", i + 1)
            ',' -> {
                result.add(accumulator.toString())
                accumulator.clear()
                i = indexOfFirst(i + 1, endInternal) { !it.isWhitespace() }
                continue
            }
        }

        accumulator.append(if (prev == i) cur else substring(prev..i))
        i++
    }
    result.add(accumulator.toString())
    return result
}

private inline fun <reified N> Any?.castTo(): N {
    if (this == null) throw NBTDecodingException("Decoding returned null")
    return try {
        this as N
    } catch (exc: ClassCastException) {
        throw NBTDecodingException("Data Type mismatch", exc)
    }
}

internal fun payloadNodeSupplierFor(type: TagType): (Any?) -> PayloadNode<*> {
    return when (type) {
        TagType.END -> {
            { EndPayloadNode }
        }
        TagType.BYTE -> {
            { BytePayloadNode(it.castTo()) }
        }
        TagType.SHORT -> {
            { ShortPayloadNode(it.castTo()) }
        }
        TagType.INT -> {
            { IntPayloadNode(it.castTo()) }
        }
        TagType.LONG -> {
            { LongPayloadNode(it.castTo()) }
        }
        TagType.FLOAT -> {
            { FloatPayloadNode(it.castTo()) }
        }
        TagType.DOUBLE -> {
            { DoublePayloadNode(it.castTo()) }
        }
        TagType.BYTEARRAY -> {
            { ByteArrayPayloadNode(it.castTo()) }
        }
        TagType.STRING -> {
            { StringPayloadNode(it.castTo()) }
        }
        TagType.LIST -> {
            { ListPayloadNode(it.castTo()) }
        }
        TagType.COMPOUND -> {
            { CompoundPayloadNode(it.castTo()) }
        }
        TagType.INTARRAY -> {
            { IntArrayPayloadNode(it.castTo()) }
        }
        TagType.LONGARRAY -> {
            { LongArrayPayloadNode(it.castTo()) }
        }
    }
}

internal fun guessTagTypeFor(value: String, extractValue: Boolean = false): TagType {
    val prepared = if (extractValue) {
        TagDecoder.snbtRegex.matchEntire(value)?.groupValues?.get(2) ?: throw SNBTDecodingException(
            "Cannot guess type of element: ${value.truncate(50)}"
        )
    } else value

    return when {
        prepared.matches(BytePayloadDecoder.regexSignature) -> TagType.BYTE
        prepared.matches(ShortPayloadDecoder.regexSignature) -> TagType.SHORT
        prepared.matches(LongPayloadDecoder.regexSignature) -> TagType.LONG
        prepared.matches(FloatPayloadDecoder.regexSignature) -> TagType.FLOAT
        prepared.matches(DoublePayloadDecoder.regexSignature) -> TagType.DOUBLE
        prepared.matches(IntPayloadDecoder.regexSignature) -> TagType.INT
        prepared.matches(ByteArrayPayloadDecoder.regexSignature) -> TagType.BYTEARRAY
        prepared.matches(IntArrayPayloadDecoder.regexSignature) -> TagType.INTARRAY
        prepared.matches(LongArrayPayloadDecoder.regexSignature) -> TagType.LONGARRAY
        prepared.matches(CompoundPayloadDecoder.regexSignature) -> TagType.COMPOUND
        prepared.matches(ListPayloadDecoder.regexSignature) -> TagType.LIST
        prepared.matches(StringPayloadDecoder.regexSignature) -> TagType.STRING
        else -> throw SNBTDecodingException(
            "Cannot guess type of element: ${value.truncate(50)}"
        )
    }
}

internal fun guessTagTypeFor(values: Iterable<String>, extractValues: Boolean = false): TagType {
    return values.map { guessTagTypeFor(it, extractValues) }.distinct().singleOrNull() ?: throw SNBTDecodingException(
        "Cannot guess list type of elements: ${values.joinToString(", ", limit = 50)}"
    )
}