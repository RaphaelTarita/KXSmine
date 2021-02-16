package msw.extras.kxsmine.tree.node.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.tag.ByteArrayTagDecoder
import msw.extras.kxsmine.tree.decoding.tag.TagDecoder
import msw.extras.kxsmine.tree.encoding.tag.ByteArrayTagEncoder
import msw.extras.kxsmine.tree.encoding.tag.TagEncoder
import msw.extras.kxsmine.tree.node.payload.ByteArrayPayloadNode
import msw.extras.kxsmine.tree.node.payload.PayloadNode

public class ByteArrayTagNode(name: String, data: ByteArray) : TagNode<ByteArray>(name) {
    override val type: TagType = TagType.BYTEARRAY
    override val payload: PayloadNode<ByteArray> = ByteArrayPayloadNode(data)
    override val encoder: TagEncoder<ByteArray> = ByteArrayTagEncoder
    override val decoder: TagDecoder<ByteArray> = ByteArrayTagDecoder
}