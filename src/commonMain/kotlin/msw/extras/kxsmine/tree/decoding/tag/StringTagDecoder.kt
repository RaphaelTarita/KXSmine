package msw.extras.kxsmine.tree.decoding.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.payload.StringPayloadDecoder
import msw.extras.kxsmine.tree.node.tag.StringTagNode
import msw.extras.kxsmine.tree.node.tag.TagNode

public object StringTagDecoder : TagDecoder<String>() {
    override val type: TagType = TagType.STRING
    override val payloadDecoder: StringPayloadDecoder = StringPayloadDecoder
    override val nodeSupplier: (String, String) -> TagNode<String> = ::StringTagNode
}