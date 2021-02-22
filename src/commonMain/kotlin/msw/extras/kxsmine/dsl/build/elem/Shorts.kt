package msw.extras.kxsmine.dsl.build.elem

import msw.extras.kxsmine.castTo
import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.node.payload.PayloadNode
import msw.extras.kxsmine.tree.node.payload.ShortPayloadNode

public object Shorts : ElementType<Short>() {
    override val type: TagType = TagType.SHORT
    override val ctor: (Short) -> ShortPayloadNode = ::ShortPayloadNode
    override val ctorGeneric: (Any?) -> PayloadNode<*> = { ctor(it.castTo()) }
}