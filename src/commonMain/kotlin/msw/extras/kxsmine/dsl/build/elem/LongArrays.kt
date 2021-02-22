package msw.extras.kxsmine.dsl.build.elem

import msw.extras.kxsmine.castTo
import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.node.payload.LongArrayPayloadNode
import msw.extras.kxsmine.tree.node.payload.PayloadNode

public object LongArrays : ElementType<LongArray>() {
    override val type: TagType = TagType.LONGARRAY
    override val ctor: (LongArray) -> LongArrayPayloadNode = ::LongArrayPayloadNode
    override val ctorGeneric: (Any?) -> PayloadNode<*> = { ctor(it.castTo()) }
}