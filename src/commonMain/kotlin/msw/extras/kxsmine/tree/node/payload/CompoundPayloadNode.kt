package msw.extras.kxsmine.tree.node.payload

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.payload.CompoundPayloadDecoder
import msw.extras.kxsmine.tree.decoding.payload.PayloadDecoder
import msw.extras.kxsmine.tree.encoding.payload.CompoundPayloadEncoder
import msw.extras.kxsmine.tree.encoding.payload.PayloadEncoder
import msw.extras.kxsmine.tree.node.tag.TagNode

public class CompoundPayloadNode(data: Collection<TagNode<*>>) : PayloadNode<Collection<TagNode<*>>>(data) {
    override val type: TagType = TagType.COMPOUND
    override val encoder: PayloadEncoder<Collection<TagNode<*>>> = CompoundPayloadEncoder
    override val decoder: PayloadDecoder<Collection<TagNode<*>>> = CompoundPayloadDecoder
}