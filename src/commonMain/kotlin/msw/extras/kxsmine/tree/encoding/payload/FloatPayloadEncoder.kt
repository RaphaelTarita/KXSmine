package msw.extras.kxsmine.tree.encoding.payload

import msw.extras.kxsmine.toByteArray
import msw.extras.kxsmine.tree.node.payload.PayloadNode

public object FloatPayloadEncoder : PayloadEncoder<Float>() {
    override fun encode(node: PayloadNode<Float>): ByteArray = node.data.toRawBits().toByteArray()
    override fun encodeSNBT(node: PayloadNode<Float>): String = "${node.data}f"
}