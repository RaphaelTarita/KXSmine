package msw.extras.kxsmine.tree.node.payload

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.payload.ByteArrayPayloadDecoder
import msw.extras.kxsmine.tree.decoding.payload.PayloadDecoder
import msw.extras.kxsmine.tree.encoding.payload.ByteArrayPayloadEncoder
import msw.extras.kxsmine.tree.encoding.payload.PayloadEncoder

public class ByteArrayPayloadNode(data: ByteArray) : PayloadNode<ByteArray>(data) {
    override val type: TagType = TagType.BYTEARRAY
    override val encoder: PayloadEncoder<ByteArray> = ByteArrayPayloadEncoder
    override val decoder: PayloadDecoder<ByteArray> = ByteArrayPayloadDecoder
}