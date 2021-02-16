package msw.extras.kxsmine.tree.node.payload

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.payload.IntPayloadDecoder
import msw.extras.kxsmine.tree.decoding.payload.PayloadDecoder
import msw.extras.kxsmine.tree.encoding.payload.IntPayloadEncoder
import msw.extras.kxsmine.tree.encoding.payload.PayloadEncoder

public class IntPayloadNode(data: Int) : PayloadNode<Int>(data) {
    override val type: TagType = TagType.INT
    override val encoder: PayloadEncoder<Int> = IntPayloadEncoder
    override val decoder: PayloadDecoder<Int> = IntPayloadDecoder
}