package msw.extras.kxsmine.dsl.build.elem

import msw.extras.kxsmine.castTo
import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.node.payload.IntPayloadNode
import msw.extras.kxsmine.tree.node.payload.PayloadNode

public object Ints : ElementType<Int>() {
    override val type: TagType = TagType.INT
    override val ctor: (Int) -> IntPayloadNode = ::IntPayloadNode
    override val ctorGeneric: (Any?) -> PayloadNode<*> = { ctor(it.castTo()) }
}