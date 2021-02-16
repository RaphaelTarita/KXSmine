package msw.extras.kxsmine.tree.node.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.tag.DoubleTagDecoder
import msw.extras.kxsmine.tree.decoding.tag.TagDecoder
import msw.extras.kxsmine.tree.encoding.tag.DoubleTagEncoder
import msw.extras.kxsmine.tree.encoding.tag.TagEncoder
import msw.extras.kxsmine.tree.node.payload.DoublePayloadNode
import msw.extras.kxsmine.tree.node.payload.PayloadNode

public class DoubleTagNode(name: String, data: Double) : TagNode<Double>(name) {
    override val type: TagType = TagType.DOUBLE
    override val payload: PayloadNode<Double> = DoublePayloadNode(data)
    override val encoder: TagEncoder<Double> = DoubleTagEncoder
    override val decoder: TagDecoder<Double> = DoubleTagDecoder
}