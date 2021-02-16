package msw.extras.kxsmine.tree.encoding.payload

import msw.extras.kxsmine.toByteArray
import msw.extras.kxsmine.tree.node.payload.PayloadNode

public object LongPayloadEncoder : PayloadEncoder<Long>() {
    override fun encode(node: PayloadNode<Long>): ByteArray = node.data.toByteArray()
    override fun encodeSNBT(node: PayloadNode<Long>): String = "${node.data}l"
}