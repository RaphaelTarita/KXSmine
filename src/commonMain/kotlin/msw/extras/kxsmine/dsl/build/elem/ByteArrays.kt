package msw.extras.kxsmine.dsl.build.elem

import msw.extras.kxsmine.castTo
import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.node.payload.ByteArrayPayloadNode
import msw.extras.kxsmine.tree.node.payload.PayloadNode

public object ByteArrays : ElementType<ByteArray>() {
    override val type: TagType = TagType.BYTEARRAY
    override val ctor: (ByteArray) -> ByteArrayPayloadNode = ::ByteArrayPayloadNode
    override val ctorGeneric: (Any?) -> PayloadNode<*> = { ctor(it.castTo()) }
}