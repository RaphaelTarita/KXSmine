package msw.extras.kxsmine.tree.decoding.payload

import msw.extras.kxsmine.tree.OffsetResult

public object ByteArrayPayloadDecoder : PayloadDecoder<ByteArray>() {
    override val regexSignature: Regex = "\\[B;\\s*[+\\-]?[0-9]+(?:,\\s*[+\\-]?[0-9]+)*\\s*]".toRegex()
    private val splitRegex = ",\\s*".toRegex()
    override fun decode(bytes: ByteArray, offset: Int): OffsetResult<ByteArray> {
        val (size, intermediateOffset) = IntPayloadDecoder.decode(bytes, offset)
        return OffsetResult(
            bytes.sliceArray(intermediateOffset until intermediateOffset + size),
            intermediateOffset + size
        )
    }

    override fun decodeSNBT(str: String, offset: Int): OffsetResult<ByteArray> {
        val endIndex = str.indexOf(']', offset)
        val bytes = str.substring(offset until endIndex)
            .trimStart()
            .removePrefix("[B;")
            .trim()
            .split(splitRegex)
            .map { it.toByte() }
            .toByteArray()

        return OffsetResult(bytes, endIndex + 1)
    }
}