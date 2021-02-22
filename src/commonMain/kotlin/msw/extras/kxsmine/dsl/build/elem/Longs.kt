package msw.extras.kxsmine.dsl.build.elem

import msw.extras.kxsmine.castTo
import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.node.payload.LongPayloadNode
import msw.extras.kxsmine.tree.node.payload.PayloadNode

public object Longs : ElementType<Long>() {
    override val type: TagType = TagType.LONG
    override val ctor: (Long) -> LongPayloadNode = ::LongPayloadNode
    override val ctorGeneric: (Any?) -> PayloadNode<*> = { ctor(it.castTo()) }
}