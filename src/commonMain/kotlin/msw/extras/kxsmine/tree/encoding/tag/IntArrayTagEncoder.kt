package msw.extras.kxsmine.tree.encoding.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.encoding.payload.IntArrayPayloadEncoder

public object IntArrayTagEncoder : TagEncoder<IntArray>() {
    override val type: TagType = TagType.INTARRAY
    override val payloadEncoder: IntArrayPayloadEncoder = IntArrayPayloadEncoder
}