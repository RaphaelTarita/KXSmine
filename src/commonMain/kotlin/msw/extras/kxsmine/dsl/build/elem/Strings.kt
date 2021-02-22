package msw.extras.kxsmine.dsl.build.elem

import msw.extras.kxsmine.castTo
import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.node.payload.PayloadNode
import msw.extras.kxsmine.tree.node.payload.StringPayloadNode

public object Strings : ElementType<String>() {
    override val type: TagType = TagType.STRING
    override val ctor: (String) -> StringPayloadNode = ::StringPayloadNode
    override val ctorGeneric: (Any?) -> PayloadNode<*> = { ctor(it.castTo()) }
}