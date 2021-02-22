package msw.extras.kxsmine.tree.node.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.tag.CompoundTagDecoder
import msw.extras.kxsmine.tree.decoding.tag.TagDecoder
import msw.extras.kxsmine.tree.encoding.tag.CompoundTagEncoder
import msw.extras.kxsmine.tree.encoding.tag.TagEncoder
import msw.extras.kxsmine.tree.node.payload.CompoundPayloadNode
import msw.extras.kxsmine.tree.node.payload.PayloadNode

public class CompoundTagNode(
    name: String,
    data: Collection<TagNode<*>>
) : TagNode<Collection<TagNode<*>>>(name) {
    override val type: TagType = TagType.COMPOUND
    override val payload: PayloadNode<Collection<TagNode<*>>> = CompoundPayloadNode(data)
    override val encoder: TagEncoder<Collection<TagNode<*>>> = CompoundTagEncoder
    override val decoder: TagDecoder<Collection<TagNode<*>>> = CompoundTagDecoder
    internal val accessMap: Map<String, TagNode<*>> = payload.data.associateBy { it.name }
}