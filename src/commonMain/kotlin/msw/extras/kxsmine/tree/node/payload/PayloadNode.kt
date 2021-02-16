package msw.extras.kxsmine.tree.node.payload

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.payload.PayloadDecoder
import msw.extras.kxsmine.tree.encoding.payload.PayloadEncoder

public sealed class PayloadNode<T>(public val data: T) {
    public abstract val type: TagType
    public abstract val encoder: PayloadEncoder<T>
    public abstract val decoder: PayloadDecoder<T>

    public fun encode(): ByteArray = encoder.encode(this)
    public fun encodeSNBT(): String = encoder.encodeSNBT(this)
}