package msw.extras.kxsmine.tree.encoding.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.encoding.payload.StringPayloadEncoder

public object StringTagEncoder : TagEncoder<String>() {
    override val type: TagType = TagType.STRING
    override val payloadEncoder: StringPayloadEncoder = StringPayloadEncoder
}