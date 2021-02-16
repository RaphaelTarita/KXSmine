package msw.extras.kxsmine.tree.encoding.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.encoding.payload.DoublePayloadEncoder

public object DoubleTagEncoder : TagEncoder<Double>() {
    override val type: TagType = TagType.DOUBLE
    override val payloadEncoder: DoublePayloadEncoder = DoublePayloadEncoder
}