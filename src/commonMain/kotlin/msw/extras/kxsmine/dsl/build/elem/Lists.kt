package msw.extras.kxsmine.dsl.build.elem

import msw.extras.kxsmine.castTo
import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.node.payload.ListPayloadNode
import msw.extras.kxsmine.tree.node.payload.PayloadNode

public object Lists : ElementType<List<PayloadNode<*>>>() {
    override val type: TagType = TagType.LIST
    override val ctor: (List<PayloadNode<*>>) -> ListPayloadNode = ::ListPayloadNode
    override val ctorGeneric: (Any?) -> PayloadNode<*> = { ctor(it.castTo()) }
}