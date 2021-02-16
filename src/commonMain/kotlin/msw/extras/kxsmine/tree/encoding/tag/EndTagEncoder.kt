package msw.extras.kxsmine.tree.encoding.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.encoding.payload.EndPayloadEncoder
import msw.extras.kxsmine.tree.encoding.payload.PayloadEncoder
import msw.extras.kxsmine.tree.node.tag.TagNode

public object EndTagEncoder : TagEncoder<Unit>() {
    override val type: TagType = TagType.END
    override val payloadEncoder: PayloadEncoder<Unit> = EndPayloadEncoder

    override fun encode(node: TagNode<Unit>): ByteArray = byteArrayOf(node.type.id)
    override fun encodeSNBT(node: TagNode<Unit>): String = ""
}