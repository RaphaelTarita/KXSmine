package msw.extras.kxsmine.tree.encoding.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.encoding.payload.LongPayloadEncoder

public object LongTagEncoder : TagEncoder<Long>() {
    override val type: TagType = TagType.LONG
    override val payloadEncoder: LongPayloadEncoder = LongPayloadEncoder
}