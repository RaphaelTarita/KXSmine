package msw.extras.kxsmine.tree.encoding.payload

import msw.extras.kxsmine.toByteArray
import msw.extras.kxsmine.tree.node.payload.PayloadNode

public object DoublePayloadEncoder : PayloadEncoder<Double>() {
    override fun encode(node: PayloadNode<Double>): ByteArray = node.data.toRawBits().toByteArray()
    override fun encodeSNBT(node: PayloadNode<Double>): String = "${node.data}d"
}