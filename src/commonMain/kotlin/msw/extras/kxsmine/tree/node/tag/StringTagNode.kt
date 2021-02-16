package msw.extras.kxsmine.tree.node.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.tag.StringTagDecoder
import msw.extras.kxsmine.tree.decoding.tag.TagDecoder
import msw.extras.kxsmine.tree.encoding.tag.StringTagEncoder
import msw.extras.kxsmine.tree.encoding.tag.TagEncoder
import msw.extras.kxsmine.tree.node.payload.PayloadNode
import msw.extras.kxsmine.tree.node.payload.StringPayloadNode

public class StringTagNode(name: String, data: String) : TagNode<String>(name) {
    override val type: TagType = TagType.STRING
    override val payload: PayloadNode<String> = StringPayloadNode(data)
    override val encoder: TagEncoder<String> = StringTagEncoder
    override val decoder: TagDecoder<String> = StringTagDecoder
}