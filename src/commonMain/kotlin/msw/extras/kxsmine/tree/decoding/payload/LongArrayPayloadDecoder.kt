package msw.extras.kxsmine.tree.decoding.payload

import msw.extras.kxsmine.chunked
import msw.extras.kxsmine.tree.OffsetResult

public object LongArrayPayloadDecoder : PayloadDecoder<LongArray>() {
    override val regexSignature: Regex = "\\[L;\\s*[+\\-]?[0-9]+(?:,\\s*[+\\-]?[0-9]+)*]".toRegex()
    private val splitRegex = ",\\s*".toRegex()

    override fun decode(bytes: ByteArray, offset: Int): OffsetResult<LongArray> {
        val (size, intermediateOffset) = IntPayloadDecoder.decode(bytes, offset)
        val longs = bytes.chunked(8, intermediateOffset, intermediateOffset + size * 8)
            .map { LongPayloadDecoder.decode(it, 0).result }
            .toLongArray()

        return OffsetResult(longs, intermediateOffset + size * 8)
    }

    override fun decodeSNBT(str: String, offset: Int): OffsetResult<LongArray> {
        val endIndex = str.indexOf(']', offset)
        val longs = str.substring(offset until endIndex)
            .trimStart()
            .removePrefix("[L;")
            .trim()
            .split(splitRegex)
            .map { it.toLong() }
            .toLongArray()

        return OffsetResult(longs, endIndex + 1)
    }
}