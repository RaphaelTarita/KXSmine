package msw.extras.kxsmine.tree.node.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.tag.IntTagDecoder
import msw.extras.kxsmine.tree.decoding.tag.TagDecoder
import msw.extras.kxsmine.tree.encoding.tag.IntTagEncoder
import msw.extras.kxsmine.tree.encoding.tag.TagEncoder
import msw.extras.kxsmine.tree.node.payload.IntPayloadNode
import msw.extras.kxsmine.tree.node.payload.PayloadNode

public class IntTagNode(name: String, data: Int) : TagNode<Int>(name) {
    override val type: TagType = TagType.INT
    override val payload: PayloadNode<Int> = IntPayloadNode(data)
    override val encoder: TagEncoder<Int> = IntTagEncoder
    override val decoder: TagDecoder<Int> = IntTagDecoder
}