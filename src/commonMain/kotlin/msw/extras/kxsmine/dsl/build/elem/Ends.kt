package msw.extras.kxsmine.dsl.build.elem

import msw.extras.kxsmine.castTo
import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.node.payload.EndPayloadNode
import msw.extras.kxsmine.tree.node.payload.PayloadNode

public object Ends : ElementType<Unit>() {
    override val type: TagType = TagType.END
    override val ctor: (Unit) -> EndPayloadNode = { EndPayloadNode }
    override val ctorGeneric: (Any?) -> PayloadNode<*> = { ctor(it.castTo()) }
}