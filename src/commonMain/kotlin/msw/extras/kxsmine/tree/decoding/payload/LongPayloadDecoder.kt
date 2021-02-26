package msw.extras.kxsmine.tree.decoding.payload

import msw.extras.kxsmine.tree.OffsetResult
import msw.extras.kxsmine.exc.SNBTDecodingException
import msw.extras.kxsmine.tree.TagType

public object LongPayloadDecoder : PayloadDecoder<Long>() {
    private const val MASK: Long = 0x00000000000000FF
    override val regexSignature: Regex = "([+\\-]?[0-9]+)[lL]".toRegex()
    override fun decode(bytes: ByteArray, offset: Int): OffsetResult<Long> {
        return OffsetResult(
            ((bytes[offset].toLong() and MASK) shl 56) or
                    ((bytes[offset + 1].toLong() and MASK) shl 48) or
                    ((bytes[offset + 2].toLong() and MASK) shl 40) or
                    ((bytes[offset + 3].toLong() and MASK) shl 32) or
                    ((bytes[offset + 4].toLong() and MASK) shl 24) or
                    ((bytes[offset + 5].toLong() and MASK) shl 16) or
                    ((bytes[offset + 6].toLong() and MASK) shl 8) or
                    (bytes[offset + 7].toLong() and MASK),
            offset + 8
        )
    }

    override fun decodeSNBT(str: String, offset: Int): OffsetResult<Long> {
        val res = regexSignature.find(str, offset) ?: throw SNBTDecodingException(TagType.LONG, str.substring(offset))
        return OffsetResult(res.groupValues[1].toLong(), res.range.last + 1)
    }

}