package msw.extras.kxsmine.tree.encoding.payload

import msw.extras.kxsmine.tree.node.payload.PayloadNode

public object ListPayloadEncoder : PayloadEncoder<List<PayloadNode<*>>>() {
    override fun encode(node: PayloadNode<List<PayloadNode<*>>>): ByteArray {
        val overallType = node.data[0].type
        val bytes = node.data.map { it.encode() }.reduce { acc, bytes -> acc + bytes }

        val metadata = ByteArray(5)
        metadata[0] = overallType.id
        metadata[1] = (node.data.size ushr 24).toByte()
        metadata[2] = (node.data.size ushr 16).toByte()
        metadata[3] = (node.data.size ushr 8).toByte()
        metadata[4] = node.data.size.toByte()

        return metadata + bytes
    }

    override fun encodeSNBT(node: PayloadNode<List<PayloadNode<*>>>): String {
        return node.data.joinToString(",", "[", "]") { it.encodeSNBT() }
    }
}