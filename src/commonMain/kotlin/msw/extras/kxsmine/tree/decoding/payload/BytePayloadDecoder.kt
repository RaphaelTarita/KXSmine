package msw.extras.kxsmine.tree.decoding.payload

import msw.extras.kxsmine.tree.OffsetResult
import msw.extras.kxsmine.tree.SNBTDecodingException
import msw.extras.kxsmine.tree.TagType

public object BytePayloadDecoder : PayloadDecoder<Byte>() {
    override val regexSignature = "([+\\-]?[0-9]+)[bB]".toRegex()
    override fun decode(bytes: ByteArray, offset: Int): OffsetResult<Byte> = OffsetResult(bytes[offset], offset + 1)

    override fun decodeSNBT(str: String, offset: Int): OffsetResult<Byte> {
        val res = regexSignature.find(str, offset) ?: throw SNBTDecodingException(TagType.BYTE, str.substring(offset))
        return OffsetResult(res.groupValues[1].toByte(), res.range.last + 1)
    }
}