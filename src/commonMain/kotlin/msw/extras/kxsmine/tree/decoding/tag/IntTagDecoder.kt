package msw.extras.kxsmine.tree.decoding.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.payload.IntPayloadDecoder
import msw.extras.kxsmine.tree.node.tag.IntTagNode
import msw.extras.kxsmine.tree.node.tag.TagNode

public object IntTagDecoder : TagDecoder<Int>() {
    override val type: TagType = TagType.INT
    override val payloadDecoder: IntPayloadDecoder = IntPayloadDecoder
    override val nodeSupplier: (String, Int) -> TagNode<Int> = ::IntTagNode
}