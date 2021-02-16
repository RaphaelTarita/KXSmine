package msw.extras.kxsmine.tree.decoding.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.payload.LongArrayPayloadDecoder
import msw.extras.kxsmine.tree.decoding.payload.PayloadDecoder
import msw.extras.kxsmine.tree.node.tag.LongArrayTagNode
import msw.extras.kxsmine.tree.node.tag.TagNode

public object LongArrayTagDecoder : TagDecoder<LongArray>() {
    override val type: TagType = TagType.LONGARRAY
    override val payloadDecoder: PayloadDecoder<LongArray> = LongArrayPayloadDecoder
    override val nodeSupplier: (String, LongArray) -> TagNode<LongArray> = ::LongArrayTagNode
}