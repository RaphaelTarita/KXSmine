package msw.extras.kxsmine.dsl.build.elem

import msw.extras.kxsmine.castTo
import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.node.payload.FloatPayloadNode
import msw.extras.kxsmine.tree.node.payload.PayloadNode

public object Floats : ElementType<Float>() {
    override val type: TagType = TagType.FLOAT
    override val ctor: (Float) -> FloatPayloadNode = ::FloatPayloadNode
    override val ctorGeneric: (Any?) -> PayloadNode<*> = { ctor(it.castTo()) }
}