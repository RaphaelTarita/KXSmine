package msw.extras.kxsmine.tree.encoding.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.encoding.payload.CompoundPayloadEncoder
import msw.extras.kxsmine.tree.node.tag.TagNode

public object CompoundTagEncoder : TagEncoder<Collection<TagNode<*>>>() {
    override val type: TagType = TagType.COMPOUND
    override val payloadEncoder: CompoundPayloadEncoder = CompoundPayloadEncoder
}