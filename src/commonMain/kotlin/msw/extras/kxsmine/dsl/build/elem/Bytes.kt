package msw.extras.kxsmine.dsl.build.elem

import msw.extras.kxsmine.castTo
import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.node.payload.BytePayloadNode
import msw.extras.kxsmine.tree.node.payload.PayloadNode

public object Bytes : ElementType<Byte>() {
    override val type: TagType = TagType.BYTE
    override val ctor: (Byte) -> BytePayloadNode = ::BytePayloadNode
    override val ctorGeneric: (Any?) -> PayloadNode<*> = { ctor(it.castTo()) }
}