package msw.extras.kxsmine.tree.decoding.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.payload.DoublePayloadDecoder
import msw.extras.kxsmine.tree.node.tag.DoubleTagNode
import msw.extras.kxsmine.tree.node.tag.TagNode

public object DoubleTagDecoder : TagDecoder<Double>() {
    override val type: TagType = TagType.DOUBLE
    override val payloadDecoder: DoublePayloadDecoder = DoublePayloadDecoder
    override val nodeSupplier: (String, Double) -> TagNode<Double> = ::DoubleTagNode
}