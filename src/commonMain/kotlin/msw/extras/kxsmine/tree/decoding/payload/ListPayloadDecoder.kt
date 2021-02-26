package msw.extras.kxsmine.tree.decoding.payload

import msw.extras.kxsmine.guessTagTypeFor
import msw.extras.kxsmine.indexOfMatching
import msw.extras.kxsmine.payloadNodeSupplierFor
import msw.extras.kxsmine.splitTopLevel
import msw.extras.kxsmine.tree.OffsetResult
import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.node.payload.EndPayloadNode
import msw.extras.kxsmine.tree.node.payload.PayloadNode

public object ListPayloadDecoder : PayloadDecoder<List<PayloadNode<*>>>() {
    override val regexSignature: Regex = "\\[.*(?:,\\s.*)*]".toRegex()

    override fun decode(bytes: ByteArray, offset: Int): OffsetResult<List<PayloadNode<*>>> {
        val overallType = TagType.fromID(bytes[offset])
        val (length, startOffset) = IntPayloadDecoder.decode(bytes, offset + 1)
        if (overallType == TagType.END) return OffsetResult(List(length) { EndPayloadNode }, offset)
        val innerDecoder = overallType.decoder.payloadDecoder
        val nodeSupplier = payloadNodeSupplierFor(overallType)
        val result = ArrayList<PayloadNode<*>>((length / overallType.heuristicSize).coerceAtLeast(1))

        var count = 0
        var iOffset = startOffset
        while (count < length) {
            val (res, off) = innerDecoder.decode(bytes, iOffset)
            result.add(nodeSupplier(res))
            iOffset = off
            count++
        }
        return OffsetResult(result, iOffset)
    }

    override fun decodeSNBT(str: String, offset: Int): OffsetResult<List<PayloadNode<*>>> {
        val (startIndex, endIndex) = str.indexOfMatching('[', offset)
        val values = str.splitTopLevel(startIndex + 1, endIndex - 1, true)

        val overallType = guessTagTypeFor(values)
        val innerDecoder = overallType.decoder.payloadDecoder
        val nodeSupplier = payloadNodeSupplierFor(overallType)

        val res = values.map { nodeSupplier(innerDecoder.decodeSNBT(it, 0).result) }

        return OffsetResult(res, endIndex + 1)
    }
}