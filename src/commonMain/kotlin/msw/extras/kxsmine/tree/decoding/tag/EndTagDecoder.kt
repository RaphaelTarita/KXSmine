package msw.extras.kxsmine.tree.decoding.tag

import msw.extras.kxsmine.tree.NBTDecodingException
import msw.extras.kxsmine.tree.OffsetResult
import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.payload.EndPayloadDecoder
import msw.extras.kxsmine.tree.node.tag.EndTagNode
import msw.extras.kxsmine.tree.node.tag.TagNode

public object EndTagDecoder : TagDecoder<Unit>() {
    override val type: TagType = TagType.END
    override val payloadDecoder: EndPayloadDecoder = EndPayloadDecoder

    override fun decode(bytes: ByteArray, offset: Int): OffsetResult<EndTagNode> {
        if (bytes[offset] != type.id) throw NBTDecodingException(type, bytes, offset)
        return OffsetResult(EndTagNode, offset + 1)
    }

    override fun decodeSNBT(str: String, offset: Int): OffsetResult<EndTagNode> {
        return OffsetResult(EndTagNode, offset)
    }

    override val nodeSupplier: (String, Unit) -> TagNode<Unit> = { _, _ -> EndTagNode }
}