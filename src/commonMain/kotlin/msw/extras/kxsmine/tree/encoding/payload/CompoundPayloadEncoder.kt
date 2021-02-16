package msw.extras.kxsmine.tree.encoding.payload

import msw.extras.kxsmine.tree.node.payload.PayloadNode
import msw.extras.kxsmine.tree.node.tag.EndTagNode
import msw.extras.kxsmine.tree.node.tag.TagNode


public object CompoundPayloadEncoder : PayloadEncoder<Collection<TagNode<*>>>() {
    override fun encode(node: PayloadNode<Collection<TagNode<*>>>): ByteArray {
        val bytes = node.data.map { it.encode() }.reduce { acc, bytes -> acc + bytes }
        return bytes + EndTagNode.encode()
    }

    override fun encodeSNBT(node: PayloadNode<Collection<TagNode<*>>>): String {
        return node.data.joinToString(",", "{", "}") { it.encodeSNBT() }
    }
}