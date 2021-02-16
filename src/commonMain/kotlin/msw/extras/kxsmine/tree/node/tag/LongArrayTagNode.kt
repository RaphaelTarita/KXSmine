package msw.extras.kxsmine.tree.node.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.tag.LongArrayTagDecoder
import msw.extras.kxsmine.tree.decoding.tag.TagDecoder
import msw.extras.kxsmine.tree.encoding.tag.LongArrayTagEncoder
import msw.extras.kxsmine.tree.encoding.tag.TagEncoder
import msw.extras.kxsmine.tree.node.payload.LongArrayPayloadNode
import msw.extras.kxsmine.tree.node.payload.PayloadNode

public class LongArrayTagNode(name: String, data: LongArray) : TagNode<LongArray>(name) {
    override val type: TagType = TagType.LONGARRAY
    override val payload: PayloadNode<LongArray> = LongArrayPayloadNode(data)
    override val encoder: TagEncoder<LongArray> = LongArrayTagEncoder
    override val decoder: TagDecoder<LongArray> = LongArrayTagDecoder
}