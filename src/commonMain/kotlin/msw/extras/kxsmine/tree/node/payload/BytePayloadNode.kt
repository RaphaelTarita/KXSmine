package msw.extras.kxsmine.tree.node.payload

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.payload.BytePayloadDecoder
import msw.extras.kxsmine.tree.decoding.payload.PayloadDecoder
import msw.extras.kxsmine.tree.encoding.payload.BytePayloadEncoder
import msw.extras.kxsmine.tree.encoding.payload.PayloadEncoder

public class BytePayloadNode(data: Byte) : PayloadNode<Byte>(data) {
    override val type: TagType = TagType.BYTE
    override val encoder: PayloadEncoder<Byte> = BytePayloadEncoder
    override val decoder: PayloadDecoder<Byte> = BytePayloadDecoder
}