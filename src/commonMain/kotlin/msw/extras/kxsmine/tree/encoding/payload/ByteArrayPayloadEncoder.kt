package msw.extras.kxsmine.tree.encoding.payload

import msw.extras.kxsmine.toByteArray
import msw.extras.kxsmine.tree.node.payload.PayloadNode

public object ByteArrayPayloadEncoder : PayloadEncoder<ByteArray>() {
    override fun encode(node: PayloadNode<ByteArray>): ByteArray = node.data.size.toByteArray() + node.data
    override fun encodeSNBT(node: PayloadNode<ByteArray>): String = node.data.joinToString(",", "[B;", "]")
}