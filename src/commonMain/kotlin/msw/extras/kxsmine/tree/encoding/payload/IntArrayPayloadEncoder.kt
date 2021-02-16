package msw.extras.kxsmine.tree.encoding.payload

import msw.extras.kxsmine.toByteArray
import msw.extras.kxsmine.tree.node.payload.PayloadNode

public object IntArrayPayloadEncoder : PayloadEncoder<IntArray>() {
    override fun encode(node: PayloadNode<IntArray>): ByteArray {
        val bytes = node.data.map { it.toByteArray() }.reduce { acc, bytes -> acc + bytes }
        return node.data.size.toByteArray() + bytes
    }

    override fun encodeSNBT(node: PayloadNode<IntArray>): String = node.data.joinToString(",", "[I;", "]")
}