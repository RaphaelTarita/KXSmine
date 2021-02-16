package msw.extras.kxsmine.tree.node.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.tag.ShortTagDecoder
import msw.extras.kxsmine.tree.decoding.tag.TagDecoder
import msw.extras.kxsmine.tree.encoding.tag.ShortTagEncoder
import msw.extras.kxsmine.tree.encoding.tag.TagEncoder
import msw.extras.kxsmine.tree.node.payload.PayloadNode
import msw.extras.kxsmine.tree.node.payload.ShortPayloadNode

public class ShortTagNode(name: String, data: Short) : TagNode<Short>(name) {
    override val type: TagType = TagType.SHORT
    override val payload: PayloadNode<Short> = ShortPayloadNode(data)
    override val encoder: TagEncoder<Short> = ShortTagEncoder
    override val decoder: TagDecoder<Short> = ShortTagDecoder
}