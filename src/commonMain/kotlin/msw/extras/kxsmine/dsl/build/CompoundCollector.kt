package msw.extras.kxsmine.dsl.build

import msw.extras.kxsmine.tree.node.payload.CompoundPayloadNode
import msw.extras.kxsmine.tree.node.tag.TagNode

@NBTMarker
public interface CompoundCollector {
    public fun compound(data: Collection<TagNode<*>>): CompoundPayloadNode
    public fun compound(vararg data: TagNode<*>): CompoundPayloadNode
    public fun compound(builder: NBTContext.() -> Unit): CompoundPayloadNode
}