package msw.extras.kxsmine.dsl.build.elem

import msw.extras.kxsmine.castTo
import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.node.payload.CompoundPayloadNode
import msw.extras.kxsmine.tree.node.payload.PayloadNode
import msw.extras.kxsmine.tree.node.tag.TagNode

public object Compounds : ElementType<Collection<TagNode<*>>>() {
    override val type: TagType = TagType.COMPOUND
    override val ctor: (Collection<TagNode<*>>) -> CompoundPayloadNode = ::CompoundPayloadNode
    override val ctorGeneric: (Any?) -> PayloadNode<*> = { ctor(it.castTo()) }
}