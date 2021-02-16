package msw.extras.kxsmine.tree.decoding.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.payload.LongPayloadDecoder
import msw.extras.kxsmine.tree.node.tag.LongTagNode
import msw.extras.kxsmine.tree.node.tag.TagNode

public object LongTagDecoder : TagDecoder<Long>() {
    override val type: TagType = TagType.LONG
    override val payloadDecoder: LongPayloadDecoder = LongPayloadDecoder
    override val nodeSupplier: (String, Long) -> TagNode<Long> = ::LongTagNode
}