package msw.extras.kxsmine.tree.node.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.tag.FloatTagDecoder
import msw.extras.kxsmine.tree.decoding.tag.TagDecoder
import msw.extras.kxsmine.tree.encoding.tag.FloatTagEncoder
import msw.extras.kxsmine.tree.encoding.tag.TagEncoder
import msw.extras.kxsmine.tree.node.payload.FloatPayloadNode
import msw.extras.kxsmine.tree.node.payload.PayloadNode

public class FloatTagNode(name: String, data: Float) : TagNode<Float>(name) {
    override val type: TagType = TagType.FLOAT
    override val payload: PayloadNode<Float> = FloatPayloadNode(data)
    override val encoder: TagEncoder<Float> = FloatTagEncoder
    override val decoder: TagDecoder<Float> = FloatTagDecoder
}