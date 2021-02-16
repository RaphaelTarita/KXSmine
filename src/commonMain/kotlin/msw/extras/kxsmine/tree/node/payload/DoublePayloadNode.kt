package msw.extras.kxsmine.tree.node.payload

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.payload.DoublePayloadDecoder
import msw.extras.kxsmine.tree.decoding.payload.PayloadDecoder
import msw.extras.kxsmine.tree.encoding.payload.DoublePayloadEncoder
import msw.extras.kxsmine.tree.encoding.payload.PayloadEncoder

public class DoublePayloadNode(data: Double) : PayloadNode<Double>(data) {
    override val type: TagType = TagType.DOUBLE
    override val encoder: PayloadEncoder<Double> = DoublePayloadEncoder
    override val decoder: PayloadDecoder<Double> = DoublePayloadDecoder
}