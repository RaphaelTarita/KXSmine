package msw.extras.kxsmine.tree.encoding.payload

import msw.extras.kxsmine.tree.node.payload.PayloadNode

public object EndPayloadEncoder : PayloadEncoder<Unit>() {
    override fun encode(node: PayloadNode<Unit>): ByteArray = ByteArray(0)
    override fun encodeSNBT(node: PayloadNode<Unit>): String = ""
}