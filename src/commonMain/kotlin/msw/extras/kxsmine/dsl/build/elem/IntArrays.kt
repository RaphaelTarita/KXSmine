package msw.extras.kxsmine.dsl.build.elem

import msw.extras.kxsmine.castTo
import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.node.payload.IntArrayPayloadNode
import msw.extras.kxsmine.tree.node.payload.PayloadNode

public object IntArrays : ElementType<IntArray>() {
    override val type: TagType = TagType.INTARRAY
    override val ctor: (IntArray) -> IntArrayPayloadNode = ::IntArrayPayloadNode
    override val ctorGeneric: (Any?) -> PayloadNode<*> = { ctor(it.castTo()) }
}