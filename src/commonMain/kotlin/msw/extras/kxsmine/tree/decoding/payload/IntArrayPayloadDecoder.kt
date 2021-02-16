package msw.extras.kxsmine.tree.decoding.payload

import msw.extras.kxsmine.chunked
import msw.extras.kxsmine.tree.OffsetResult

public object IntArrayPayloadDecoder : PayloadDecoder<IntArray>() {
    override val regexSignature: Regex = "\\[I;\\s*[+\\-]?[0-9]+(?:,\\s*[+\\-]?[0-9]+)*]".toRegex()
    private val splitRegex = ",\\s*".toRegex()

    override fun decode(bytes: ByteArray, offset: Int): OffsetResult<IntArray> {
        val (size, intermediateOffset) = IntPayloadDecoder.decode(bytes, offset)
        val ints = bytes.chunked(4, intermediateOffset, intermediateOffset + size * 4)
            .map { IntPayloadDecoder.decode(it, 0).result }
            .toIntArray()

        return OffsetResult(ints, intermediateOffset + size * 4)
    }

    override fun decodeSNBT(str: String, offset: Int): OffsetResult<IntArray> {
        val endIndex = str.indexOf(']', offset)
        val ints = str.substring(offset until endIndex)
            .trimStart()
            .removePrefix("[I;")
            .trim()
            .split(splitRegex)
            .map { it.toInt() }
            .toIntArray()

        return OffsetResult(ints, endIndex + 1)
    }
}