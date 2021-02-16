package msw.extras.kxsmine.tree.encoding.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.encoding.payload.LongArrayPayloadEncoder

public object LongArrayTagEncoder : TagEncoder<LongArray>() {
    override val type: TagType = TagType.LONGARRAY
    override val payloadEncoder: LongArrayPayloadEncoder = LongArrayPayloadEncoder
}