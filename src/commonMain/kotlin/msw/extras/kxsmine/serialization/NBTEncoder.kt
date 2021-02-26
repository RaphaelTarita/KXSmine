package msw.extras.kxsmine.serialization

import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.encoding.Encoder
import msw.extras.kxsmine.tree.node.payload.PayloadNode
import msw.extras.kxsmine.tree.node.tag.TagNode

public interface NBTEncoder : Encoder, CompositeEncoder {
    public val notifySuper: (sub: NBTEncoder) -> Unit
    public val collector: MutableList<*>
    public fun extractRootTag(): TagNode<*>
    public fun extractRootPayload(): PayloadNode<*>
}