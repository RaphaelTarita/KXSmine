package msw.extras.kxsmine.tree.encoding.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.encoding.payload.IntPayloadEncoder

public object IntTagEncoder : TagEncoder<Int>() {
    override val type: TagType = TagType.INT
    override val payloadEncoder: IntPayloadEncoder = IntPayloadEncoder
}