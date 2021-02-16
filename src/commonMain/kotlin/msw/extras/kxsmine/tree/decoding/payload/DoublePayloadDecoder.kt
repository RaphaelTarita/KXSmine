package msw.extras.kxsmine.tree.decoding.payload

import msw.extras.kxsmine.tree.OffsetResult
import msw.extras.kxsmine.tree.SNBTDecodingException
import msw.extras.kxsmine.tree.TagType

public object DoublePayloadDecoder : PayloadDecoder<Double>() {
    override val regexSignature: Regex = "([+\\-]?[0-9]*(?:\\.[0-9]+)?)[dD]".toRegex()
    override fun decode(bytes: ByteArray, offset: Int): OffsetResult<Double> {
        val (rawBits, newOffset) = LongPayloadDecoder.decode(bytes, offset)
        return OffsetResult(Double.fromBits(rawBits), newOffset)
    }

    override fun decodeSNBT(str: String, offset: Int): OffsetResult<Double> {
        val res = regexSignature.find(str, offset) ?: throw SNBTDecodingException(TagType.DOUBLE, str.substring(offset))
        return OffsetResult(res.groupValues[1].toDouble(), res.range.last + 1)
    }
}