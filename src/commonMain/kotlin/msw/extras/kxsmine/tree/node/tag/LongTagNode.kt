package msw.extras.kxsmine.tree.node.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.tag.LongTagDecoder
import msw.extras.kxsmine.tree.decoding.tag.TagDecoder
import msw.extras.kxsmine.tree.encoding.tag.LongTagEncoder
import msw.extras.kxsmine.tree.encoding.tag.TagEncoder
import msw.extras.kxsmine.tree.node.payload.LongPayloadNode
import msw.extras.kxsmine.tree.node.payload.PayloadNode

public class LongTagNode(name: String, data: Long) : TagNode<Long>(name) {
    override val type: TagType = TagType.LONG
    override val payload: PayloadNode<Long> = LongPayloadNode(data)
    override val encoder: TagEncoder<Long> = LongTagEncoder
    override val decoder: TagDecoder<Long> = LongTagDecoder
}