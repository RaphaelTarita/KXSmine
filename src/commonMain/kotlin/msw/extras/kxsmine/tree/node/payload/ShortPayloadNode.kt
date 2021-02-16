package msw.extras.kxsmine.tree.node.payload

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.payload.PayloadDecoder
import msw.extras.kxsmine.tree.decoding.payload.ShortPayloadDecoder
import msw.extras.kxsmine.tree.encoding.payload.PayloadEncoder
import msw.extras.kxsmine.tree.encoding.payload.ShortPayloadEncoder

public class ShortPayloadNode(data: Short) : PayloadNode<Short>(data) {
    override val type: TagType = TagType.SHORT
    override val encoder: PayloadEncoder<Short> = ShortPayloadEncoder
    override val decoder: PayloadDecoder<Short> = ShortPayloadDecoder
}