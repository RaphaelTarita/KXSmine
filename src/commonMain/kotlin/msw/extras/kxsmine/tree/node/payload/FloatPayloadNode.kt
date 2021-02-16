package msw.extras.kxsmine.tree.node.payload

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.payload.FloatPayloadDecoder
import msw.extras.kxsmine.tree.decoding.payload.PayloadDecoder
import msw.extras.kxsmine.tree.encoding.payload.FloatPayloadEncoder
import msw.extras.kxsmine.tree.encoding.payload.PayloadEncoder

public class FloatPayloadNode(data: Float) : PayloadNode<Float>(data) {
    override val type: TagType = TagType.FLOAT
    override val encoder: PayloadEncoder<Float> = FloatPayloadEncoder
    override val decoder: PayloadDecoder<Float> = FloatPayloadDecoder
}