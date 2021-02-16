package msw.extras.kxsmine.tree.encoding.payload

import msw.extras.kxsmine.toByteArray
import msw.extras.kxsmine.tree.node.payload.PayloadNode

public object IntPayloadEncoder : PayloadEncoder<Int>() {
    override fun encode(node: PayloadNode<Int>): ByteArray = node.data.toByteArray()
    override fun encodeSNBT(node: PayloadNode<Int>): String = node.data.toString()
}