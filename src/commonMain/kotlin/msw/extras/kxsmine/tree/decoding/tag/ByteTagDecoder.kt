package msw.extras.kxsmine.tree.decoding.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.payload.BytePayloadDecoder
import msw.extras.kxsmine.tree.node.tag.ByteTagNode
import msw.extras.kxsmine.tree.node.tag.TagNode

public object ByteTagDecoder : TagDecoder<Byte>() {
    override val type: TagType = TagType.BYTE
    override val payloadDecoder: BytePayloadDecoder = BytePayloadDecoder
    override val nodeSupplier: (String, Byte) -> TagNode<Byte> = ::ByteTagNode
}