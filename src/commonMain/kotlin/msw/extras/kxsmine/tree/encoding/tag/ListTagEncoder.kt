package msw.extras.kxsmine.tree.encoding.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.encoding.payload.ListPayloadEncoder
import msw.extras.kxsmine.tree.node.payload.PayloadNode

public object ListTagEncoder : TagEncoder<List<PayloadNode<*>>>() {
    override val type: TagType = TagType.LIST
    override val payloadEncoder: ListPayloadEncoder = ListPayloadEncoder
}