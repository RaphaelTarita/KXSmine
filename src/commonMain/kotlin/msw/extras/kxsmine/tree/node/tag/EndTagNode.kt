package msw.extras.kxsmine.tree.node.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.tag.EndTagDecoder
import msw.extras.kxsmine.tree.decoding.tag.TagDecoder
import msw.extras.kxsmine.tree.encoding.tag.EndTagEncoder
import msw.extras.kxsmine.tree.encoding.tag.TagEncoder
import msw.extras.kxsmine.tree.node.payload.EndPayloadNode
import msw.extras.kxsmine.tree.node.payload.PayloadNode

public object EndTagNode : TagNode<Unit>("") {
    override val type: TagType = TagType.END
    override val payload: PayloadNode<Unit> = EndPayloadNode
    override val encoder: TagEncoder<Unit> = EndTagEncoder
    override val decoder: TagDecoder<Unit> = EndTagDecoder
}