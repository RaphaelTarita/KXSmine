package msw.extras.kxsmine.tree.decoding.payload

import msw.extras.kxsmine.tree.OffsetResult
import msw.extras.kxsmine.exc.SNBTDecodingException
import msw.extras.kxsmine.tree.TagType

public object ShortPayloadDecoder : PayloadDecoder<Short>() {
    private const val MASK: Int = 0x000000FF
    override val regexSignature: Regex = "([+\\-]?[0-9]+)[sS]".toRegex()
    override fun decode(bytes: ByteArray, offset: Int): OffsetResult<Short> {
        return OffsetResult(
            (((bytes[offset].toInt() and MASK) shl 8) or
                    (bytes[offset + 1].toInt() and MASK)).toShort(),
            offset + 2
        )
    }

    override fun decodeSNBT(str: String, offset: Int): OffsetResult<Short> {
        val res = regexSignature.find(str, offset) ?: throw SNBTDecodingException(TagType.SHORT, str.substring(offset))
        return OffsetResult(res.groupValues[1].toShort(), res.range.last + 1)
    }
}