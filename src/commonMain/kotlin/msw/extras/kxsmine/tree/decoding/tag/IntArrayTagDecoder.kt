package msw.extras.kxsmine.tree.decoding.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.payload.IntArrayPayloadDecoder
import msw.extras.kxsmine.tree.decoding.payload.PayloadDecoder
import msw.extras.kxsmine.tree.node.tag.IntArrayTagNode
import msw.extras.kxsmine.tree.node.tag.TagNode

public object IntArrayTagDecoder : TagDecoder<IntArray>() {
    override val type: TagType = TagType.INTARRAY
    override val payloadDecoder: PayloadDecoder<IntArray> = IntArrayPayloadDecoder
    override val nodeSupplier: (String, IntArray) -> TagNode<IntArray> = ::IntArrayTagNode
}