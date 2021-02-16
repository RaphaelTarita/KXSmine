package msw.extras.kxsmine.tree.decoding.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.payload.FloatPayloadDecoder
import msw.extras.kxsmine.tree.node.tag.FloatTagNode
import msw.extras.kxsmine.tree.node.tag.TagNode

public object FloatTagDecoder : TagDecoder<Float>() {
    override val type: TagType = TagType.FLOAT
    override val payloadDecoder: FloatPayloadDecoder = FloatPayloadDecoder
    override val nodeSupplier: (String, Float) -> TagNode<Float> = ::FloatTagNode
}