package msw.extras.kxsmine.tree.encoding.payload

import msw.extras.kxsmine.tree.node.payload.PayloadNode

public object ShortPayloadEncoder : PayloadEncoder<Short>() {
    override fun encode(node: PayloadNode<Short>): ByteArray {
        return byteArrayOf(
            (node.data.toInt() ushr 8).toByte(),
            node.data.toByte()
        )
    }

    override fun encodeSNBT(node: PayloadNode<Short>): String = "${node.data}s"
}