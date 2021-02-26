package msw.extras.kxsmine.tree.decoding.payload

import msw.extras.kxsmine.tree.OffsetResult
import msw.extras.kxsmine.exc.SNBTDecodingException
import msw.extras.kxsmine.tree.TagType

public object FloatPayloadDecoder : PayloadDecoder<Float>() {
    override val regexSignature: Regex = "([+\\-]?[0-9]*(?:\\.[0-9]+)?)[fF]".toRegex()
    override fun decode(bytes: ByteArray, offset: Int): OffsetResult<Float> {
        val (rawBits, newOffset) = IntPayloadDecoder.decode(bytes, offset)
        return OffsetResult(Float.fromBits(rawBits), newOffset)
    }

    override fun decodeSNBT(str: String, offset: Int): OffsetResult<Float> {
        val res = regexSignature.find(str, offset) ?: throw SNBTDecodingException(TagType.FLOAT, str.substring(offset))
        return OffsetResult(res.groupValues[1].toFloat(), res.range.last + 1)
    }
}