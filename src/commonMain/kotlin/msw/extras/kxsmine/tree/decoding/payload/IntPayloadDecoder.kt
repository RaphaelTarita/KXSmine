package msw.extras.kxsmine.tree.decoding.payload

import msw.extras.kxsmine.tree.OffsetResult
import msw.extras.kxsmine.exc.SNBTDecodingException
import msw.extras.kxsmine.tree.TagType

public object IntPayloadDecoder : PayloadDecoder<Int>() {
    private const val MASK: Int = 0x000000FF
    override val regexSignature: Regex = "[+\\-]?[0-9]+".toRegex()
    override fun decode(bytes: ByteArray, offset: Int): OffsetResult<Int> {
        return OffsetResult(
            ((bytes[offset].toInt() and MASK) shl 24) or
                    ((bytes[offset + 1].toInt() and MASK) shl 16) or
                    ((bytes[offset + 2].toInt() and MASK) shl 8) or
                    (bytes[offset + 3].toInt() and MASK),
            offset + 4
        )
    }

    override fun decodeSNBT(str: String, offset: Int): OffsetResult<Int> {
        val res = regexSignature.find(str, offset) ?: throw SNBTDecodingException(TagType.INT, str.substring(offset))
        return OffsetResult(res.groupValues[0].toInt(), res.range.last)
    }
}