package msw.extras.kxsmine.tree.node.payload

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.payload.LongPayloadDecoder
import msw.extras.kxsmine.tree.decoding.payload.PayloadDecoder
import msw.extras.kxsmine.tree.encoding.payload.LongPayloadEncoder
import msw.extras.kxsmine.tree.encoding.payload.PayloadEncoder

public class LongPayloadNode(data: Long) : PayloadNode<Long>(data) {
    override val type: TagType = TagType.LONG
    override val encoder: PayloadEncoder<Long> = LongPayloadEncoder
    override val decoder: PayloadDecoder<Long> = LongPayloadDecoder
}