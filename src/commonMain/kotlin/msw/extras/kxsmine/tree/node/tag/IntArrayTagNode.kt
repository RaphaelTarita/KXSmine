package msw.extras.kxsmine.tree.node.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.tag.IntArrayTagDecoder
import msw.extras.kxsmine.tree.decoding.tag.TagDecoder
import msw.extras.kxsmine.tree.encoding.tag.IntArrayTagEncoder
import msw.extras.kxsmine.tree.encoding.tag.TagEncoder
import msw.extras.kxsmine.tree.node.payload.IntArrayPayloadNode
import msw.extras.kxsmine.tree.node.payload.PayloadNode

public class IntArrayTagNode(name: String, data: IntArray) : TagNode<IntArray>(name) {
    override val type: TagType = TagType.INTARRAY
    override val payload: PayloadNode<IntArray> = IntArrayPayloadNode(data)
    override val encoder: TagEncoder<IntArray> = IntArrayTagEncoder
    override val decoder: TagDecoder<IntArray> = IntArrayTagDecoder
}