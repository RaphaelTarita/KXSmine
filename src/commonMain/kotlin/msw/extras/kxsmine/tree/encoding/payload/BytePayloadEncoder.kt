package msw.extras.kxsmine.tree.encoding.payload

import msw.extras.kxsmine.tree.node.payload.PayloadNode

public object BytePayloadEncoder : PayloadEncoder<Byte>() {
    override fun encode(node: PayloadNode<Byte>): ByteArray = byteArrayOf(node.data)
    override fun encodeSNBT(node: PayloadNode<Byte>): String = "${node.data}b"
}