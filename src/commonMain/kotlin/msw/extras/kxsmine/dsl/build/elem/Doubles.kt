package msw.extras.kxsmine.dsl.build.elem

import msw.extras.kxsmine.castTo
import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.node.payload.DoublePayloadNode
import msw.extras.kxsmine.tree.node.payload.PayloadNode

public object Doubles : ElementType<Double>() {
    override val type: TagType = TagType.DOUBLE
    override val ctor: (Double) -> DoublePayloadNode = ::DoublePayloadNode
    override val ctorGeneric: (Any?) -> PayloadNode<*> = { ctor(it.castTo()) }
}