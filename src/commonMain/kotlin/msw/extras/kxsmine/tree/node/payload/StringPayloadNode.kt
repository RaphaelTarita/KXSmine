package msw.extras.kxsmine.tree.node.payload

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.payload.PayloadDecoder
import msw.extras.kxsmine.tree.decoding.payload.StringPayloadDecoder
import msw.extras.kxsmine.tree.encoding.payload.PayloadEncoder
import msw.extras.kxsmine.tree.encoding.payload.StringPayloadEncoder

public class StringPayloadNode(data: String) : PayloadNode<String>(data) {
    override val type: TagType = TagType.STRING
    override val encoder: PayloadEncoder<String> = StringPayloadEncoder
    override val decoder: PayloadDecoder<String> = StringPayloadDecoder
}