package msw.extras.kxsmine.tree.node.payload

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.payload.LongArrayPayloadDecoder
import msw.extras.kxsmine.tree.decoding.payload.PayloadDecoder
import msw.extras.kxsmine.tree.encoding.payload.LongArrayPayloadEncoder
import msw.extras.kxsmine.tree.encoding.payload.PayloadEncoder

public class LongArrayPayloadNode(data: LongArray) : PayloadNode<LongArray>(data) {
    override val type: TagType = TagType.LONGARRAY
    override val encoder: PayloadEncoder<LongArray> = LongArrayPayloadEncoder
    override val decoder: PayloadDecoder<LongArray> = LongArrayPayloadDecoder
}