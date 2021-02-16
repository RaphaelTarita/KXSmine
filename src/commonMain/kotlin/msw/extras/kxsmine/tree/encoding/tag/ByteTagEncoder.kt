package msw.extras.kxsmine.tree.encoding.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.encoding.payload.BytePayloadEncoder

public object ByteTagEncoder : TagEncoder<Byte>() {
    override val type: TagType = TagType.BYTE
    override val payloadEncoder: BytePayloadEncoder = BytePayloadEncoder
}