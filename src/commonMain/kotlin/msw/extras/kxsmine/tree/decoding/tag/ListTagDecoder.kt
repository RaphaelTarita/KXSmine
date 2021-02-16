package msw.extras.kxsmine.tree.decoding.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.payload.ListPayloadDecoder
import msw.extras.kxsmine.tree.node.payload.PayloadNode
import msw.extras.kxsmine.tree.node.tag.ListTagNode
import msw.extras.kxsmine.tree.node.tag.TagNode

public object ListTagDecoder : TagDecoder<List<PayloadNode<*>>>() {
    override val type: TagType = TagType.LIST
    override val payloadDecoder: ListPayloadDecoder = ListPayloadDecoder
    override val nodeSupplier: (String, List<PayloadNode<*>>) -> TagNode<List<PayloadNode<*>>> = ::ListTagNode
}