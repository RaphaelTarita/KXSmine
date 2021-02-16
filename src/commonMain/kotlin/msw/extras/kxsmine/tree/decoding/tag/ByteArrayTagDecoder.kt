package msw.extras.kxsmine.tree.decoding.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.payload.ByteArrayPayloadDecoder
import msw.extras.kxsmine.tree.node.tag.ByteArrayTagNode
import msw.extras.kxsmine.tree.node.tag.TagNode

public object ByteArrayTagDecoder : TagDecoder<ByteArray>() {
    override val type: TagType = TagType.BYTEARRAY
    override val payloadDecoder: ByteArrayPayloadDecoder = ByteArrayPayloadDecoder
    override val nodeSupplier: (String, ByteArray) -> TagNode<ByteArray> = ::ByteArrayTagNode
}