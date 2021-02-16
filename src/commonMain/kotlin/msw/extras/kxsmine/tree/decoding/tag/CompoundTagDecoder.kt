package msw.extras.kxsmine.tree.decoding.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.payload.CompoundPayloadDecoder
import msw.extras.kxsmine.tree.node.tag.CompoundTagNode
import msw.extras.kxsmine.tree.node.tag.TagNode

public object CompoundTagDecoder : TagDecoder<Collection<TagNode<*>>>() {
    override val type: TagType = TagType.COMPOUND
    override val payloadDecoder: CompoundPayloadDecoder = CompoundPayloadDecoder
    override val nodeSupplier: (String, Collection<TagNode<*>>) -> TagNode<Collection<TagNode<*>>> =
        ::CompoundTagNode
}