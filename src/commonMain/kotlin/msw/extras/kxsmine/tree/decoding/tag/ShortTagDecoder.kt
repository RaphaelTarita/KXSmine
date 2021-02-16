package msw.extras.kxsmine.tree.decoding.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.payload.ShortPayloadDecoder
import msw.extras.kxsmine.tree.node.tag.ShortTagNode
import msw.extras.kxsmine.tree.node.tag.TagNode

public object ShortTagDecoder : TagDecoder<Short>() {
    override val type: TagType = TagType.SHORT
    override val payloadDecoder: ShortPayloadDecoder = ShortPayloadDecoder
    override val nodeSupplier: (String, Short) -> TagNode<Short> = ::ShortTagNode
}