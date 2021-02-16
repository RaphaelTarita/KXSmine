package msw.extras.kxsmine.tree.node.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.tag.ListTagDecoder
import msw.extras.kxsmine.tree.decoding.tag.TagDecoder
import msw.extras.kxsmine.tree.encoding.tag.ListTagEncoder
import msw.extras.kxsmine.tree.encoding.tag.TagEncoder
import msw.extras.kxsmine.tree.node.payload.ListPayloadNode
import msw.extras.kxsmine.tree.node.payload.PayloadNode

public class ListTagNode(name: String, data: List<PayloadNode<*>>) : TagNode<List<PayloadNode<*>>>(name) {
    override val type: TagType = TagType.LIST
    override val payload: PayloadNode<List<PayloadNode<*>>> = ListPayloadNode(data)
    override val encoder: TagEncoder<List<PayloadNode<*>>> = ListTagEncoder
    override val decoder: TagDecoder<List<PayloadNode<*>>> = ListTagDecoder
}