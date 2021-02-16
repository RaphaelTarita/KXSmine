package msw.extras.kxsmine.tree.encoding.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.encoding.payload.ShortPayloadEncoder

public object ShortTagEncoder : TagEncoder<Short>() {
    override val type: TagType = TagType.SHORT
    override val payloadEncoder: ShortPayloadEncoder = ShortPayloadEncoder
}