package msw.extras.kxsmine.tree.encoding.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.encoding.payload.FloatPayloadEncoder

public object FloatTagEncoder : TagEncoder<Float>() {
    override val type: TagType = TagType.FLOAT
    override val payloadEncoder: FloatPayloadEncoder = FloatPayloadEncoder
}