package msw.extras.kxsmine.tree.node.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.tag.ByteTagDecoder
import msw.extras.kxsmine.tree.decoding.tag.TagDecoder
import msw.extras.kxsmine.tree.encoding.tag.ByteTagEncoder
import msw.extras.kxsmine.tree.encoding.tag.TagEncoder
import msw.extras.kxsmine.tree.node.payload.BytePayloadNode
import msw.extras.kxsmine.tree.node.payload.PayloadNode

public class ByteTagNode(name: String, data: Byte) : TagNode<Byte>(name) {
    override val type: TagType = TagType.BYTE
    override val payload: PayloadNode<Byte> = BytePayloadNode(data)
    override val encoder: TagEncoder<Byte> = ByteTagEncoder
    override val decoder: TagDecoder<Byte> = ByteTagDecoder
}