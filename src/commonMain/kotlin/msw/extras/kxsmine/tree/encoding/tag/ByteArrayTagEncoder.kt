package msw.extras.kxsmine.tree.encoding.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.encoding.payload.ByteArrayPayloadEncoder

public object ByteArrayTagEncoder : TagEncoder<ByteArray>() {
    override val type: TagType = TagType.BYTEARRAY
    override val payloadEncoder: ByteArrayPayloadEncoder = ByteArrayPayloadEncoder
}