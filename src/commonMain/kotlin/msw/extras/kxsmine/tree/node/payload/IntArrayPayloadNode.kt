package msw.extras.kxsmine.tree.node.payload

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.payload.IntArrayPayloadDecoder
import msw.extras.kxsmine.tree.decoding.payload.PayloadDecoder
import msw.extras.kxsmine.tree.encoding.payload.IntArrayPayloadEncoder
import msw.extras.kxsmine.tree.encoding.payload.PayloadEncoder

public class IntArrayPayloadNode(data: IntArray) : PayloadNode<IntArray>(data) {
    override val type: TagType = TagType.INTARRAY
    override val encoder: PayloadEncoder<IntArray> = IntArrayPayloadEncoder
    override val decoder: PayloadDecoder<IntArray> = IntArrayPayloadDecoder
}