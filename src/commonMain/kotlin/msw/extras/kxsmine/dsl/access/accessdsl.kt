package msw.extras.kxsmine.dsl.access

import msw.extras.kxsmine.tree.TagIndexOutOfBoundsException
import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.node.payload.BytePayloadNode
import msw.extras.kxsmine.tree.node.payload.CompoundPayloadNode
import msw.extras.kxsmine.tree.node.payload.DoublePayloadNode
import msw.extras.kxsmine.tree.node.payload.FloatPayloadNode
import msw.extras.kxsmine.tree.node.payload.IntArrayPayloadNode
import msw.extras.kxsmine.tree.node.payload.IntPayloadNode
import msw.extras.kxsmine.tree.node.payload.ListPayloadNode
import msw.extras.kxsmine.tree.node.payload.LongArrayPayloadNode
import msw.extras.kxsmine.tree.node.payload.LongPayloadNode
import msw.extras.kxsmine.tree.node.payload.PayloadNode
import msw.extras.kxsmine.tree.node.payload.ShortPayloadNode
import msw.extras.kxsmine.tree.node.payload.StringPayloadNode
import msw.extras.kxsmine.tree.node.tag.ByteArrayTagNode
import msw.extras.kxsmine.tree.node.tag.ByteTagNode
import msw.extras.kxsmine.tree.node.tag.CompoundTagNode
import msw.extras.kxsmine.tree.node.tag.DoubleTagNode
import msw.extras.kxsmine.tree.node.tag.FloatTagNode
import msw.extras.kxsmine.tree.node.tag.IntArrayTagNode
import msw.extras.kxsmine.tree.node.tag.IntTagNode
import msw.extras.kxsmine.tree.node.tag.ListTagNode
import msw.extras.kxsmine.tree.node.tag.LongArrayTagNode
import msw.extras.kxsmine.tree.node.tag.LongTagNode
import msw.extras.kxsmine.tree.node.tag.ShortTagNode
import msw.extras.kxsmine.tree.node.tag.StringTagNode
import msw.extras.kxsmine.tree.node.tag.TagNode

// generic

public val <T> TagNode<T>.data: T
    get() = payload.data

// array access

public operator fun ByteArrayTagNode.get(index: Int): Byte {
    return payload.data.getOrNull(index)
        ?: throw TagIndexOutOfBoundsException("Index '$index' out of bounds for ByteArray '$name' (0..${payload.data.size}")
}

public operator fun IntArrayTagNode.get(index: Int): Int {
    return payload.data.getOrNull(index)
        ?: throw TagIndexOutOfBoundsException("Index '$index' out of bounds for IntArray '$name' (0..${payload.data.size}")
}

public operator fun LongArrayTagNode.get(index: Int): Long {
    return payload.data.getOrNull(index)
        ?: throw TagIndexOutOfBoundsException("Index '$index' out of bounds for LongArray '$name' (0..${payload.data.size}")
}

// list access

private fun ListTagNode.invalidIndex(index: Int): Nothing {
    throw TagIndexOutOfBoundsException("Index '$index' out of bounds for List '$name' (0..${payload.data.size})")
}

private fun ListTagNode.mismatch(index: Int, expected: TagType): Nothing {
    throw IllegalArgumentException("Nested payload with index '$index' in List '$name' is not of type '$expected'")
}

public operator fun ListTagNode.get(index: Int): PayloadNode<*> {
    return payload.data.getOrNull(index) ?: invalidIndex(index)
}

public fun ListTagNode.byteAt(index: Int): BytePayloadNode {
    val nested = get(index)
    return if (nested is BytePayloadNode) nested else mismatch(index, TagType.BYTE)
}

public fun ListTagNode.shortAt(index: Int): ShortPayloadNode {
    val nested = get(index)
    return if (nested is ShortPayloadNode) nested else mismatch(index, TagType.SHORT)
}

public fun ListTagNode.intAt(index: Int): IntPayloadNode {
    val nested = get(index)
    return if (nested is IntPayloadNode) nested else mismatch(index, TagType.INT)
}

public fun ListTagNode.longAt(index: Int): LongPayloadNode {
    val nested = get(index)
    return if (nested is LongPayloadNode) nested else mismatch(index, TagType.LONG)
}

public fun ListTagNode.floatAt(index: Int): FloatPayloadNode {
    val nested = get(index)
    return if (nested is FloatPayloadNode) nested else mismatch(index, TagType.FLOAT)
}

public fun ListTagNode.doubleAt(index: Int): DoublePayloadNode {
    val nested = get(index)
    return if (nested is DoublePayloadNode) nested else mismatch(index, TagType.DOUBLE)
}

public fun ListTagNode.stringAt(index: Int): StringPayloadNode {
    val nested = get(index)
    return if (nested is StringPayloadNode) nested else mismatch(index, TagType.STRING)
}

public fun ListTagNode.listAt(index: Int): ListPayloadNode {
    val nested = get(index)
    return if (nested is ListPayloadNode) nested else mismatch(index, TagType.LIST)
}

public fun ListTagNode.compoundAt(index: Int): CompoundPayloadNode {
    val nested = get(index)
    return if (nested is CompoundPayloadNode) nested else mismatch(index, TagType.COMPOUND)
}

public fun ListTagNode.intarrayAt(index: Int): IntArrayPayloadNode {
    val nested = get(index)
    return if (nested is IntArrayPayloadNode) nested else mismatch(index, TagType.INTARRAY)
}

public fun ListTagNode.longarrayAt(index: Int): LongArrayPayloadNode {
    val nested = get(index)
    return if (nested is LongArrayPayloadNode) nested else mismatch(index, TagType.LONGARRAY)
}

// compound access

private fun CompoundTagNode.notFound(name: String): Nothing {
    throw IllegalArgumentException("No nested tag '$name' in Compound '${this.name}'")
}

private fun CompoundTagNode.mismatch(name: String, expected: TagType): Nothing {
    throw IllegalArgumentException("Nested tag '$name' in Compound '${this.name}' is not of type '$expected'")
}

public operator fun CompoundTagNode.get(name: String): TagNode<*> {
    return accessMap[name] ?: notFound(name)
}

public fun CompoundTagNode.byte(name: String): ByteTagNode {
    val nested = get(name)
    return if (nested is ByteTagNode) nested else mismatch(name, TagType.BYTE)
}

public fun CompoundTagNode.short(name: String): ShortTagNode {
    val nested = get(name)
    return if (nested is ShortTagNode) nested else mismatch(name, TagType.SHORT)
}

public fun CompoundTagNode.int(name: String): IntTagNode {
    val nested = get(name)
    return if (nested is IntTagNode) nested else mismatch(name, TagType.INT)
}

public fun CompoundTagNode.long(name: String): LongTagNode {
    val nested = get(name)
    return if (nested is LongTagNode) nested else mismatch(name, TagType.LONG)
}

public fun CompoundTagNode.float(name: String): FloatTagNode {
    val nested = get(name)
    return if (nested is FloatTagNode) nested else mismatch(name, TagType.FLOAT)
}

public fun CompoundTagNode.double(name: String): DoubleTagNode {
    val nested = get(name)
    return if (nested is DoubleTagNode) nested else mismatch(name, TagType.DOUBLE)
}

public fun CompoundTagNode.string(name: String): StringTagNode {
    val nested = get(name)
    return if (nested is StringTagNode) nested else mismatch(name, TagType.STRING)
}

public fun CompoundTagNode.list(name: String): ListTagNode {
    val nested = get(name)
    return if (nested is ListTagNode) nested else mismatch(name, TagType.LIST)
}

public fun CompoundTagNode.compound(name: String): CompoundTagNode {
    val nested = get(name)
    return if (nested is CompoundTagNode) nested else mismatch(name, TagType.COMPOUND)
}

public fun CompoundTagNode.intarray(name: String): IntArrayTagNode {
    val nested = get(name)
    return if (nested is IntArrayTagNode) nested else mismatch(name, TagType.INTARRAY)
}

public fun CompoundTagNode.longarray(name: String): LongArrayTagNode {
    val nested = get(name)
    return if (nested is LongArrayTagNode) nested else mismatch(name, TagType.LONGARRAY)
}