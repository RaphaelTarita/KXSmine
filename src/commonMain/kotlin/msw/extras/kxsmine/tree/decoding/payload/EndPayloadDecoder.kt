package msw.extras.kxsmine.tree.decoding.payload

import msw.extras.kxsmine.tree.OffsetResult

public object EndPayloadDecoder : PayloadDecoder<Unit>() {
    override val regexSignature: Regex = "".toRegex()
    override fun decode(bytes: ByteArray, offset: Int): OffsetResult<Unit> {
        return OffsetResult(Unit, offset)
    }

    override fun decodeSNBT(str: String, offset: Int): OffsetResult<Unit> {
        return OffsetResult(Unit, offset)
    }
}