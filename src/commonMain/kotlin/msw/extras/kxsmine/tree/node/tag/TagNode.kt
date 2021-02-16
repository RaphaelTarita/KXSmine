package msw.extras.kxsmine.tree.node.tag

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.tag.TagDecoder
import msw.extras.kxsmine.tree.encoding.tag.TagEncoder
import msw.extras.kxsmine.tree.node.payload.PayloadNode

public sealed class TagNode<T>(public val name: String) {
    public abstract val type: TagType
    public abstract val payload: PayloadNode<T>
    public abstract val encoder: TagEncoder<T>
    public abstract val decoder: TagDecoder<T>

    public fun encode(): ByteArray = encoder.encode(this)
    public fun encodeSNBT(): String = encoder.encodeSNBT(this)

    internal val nameUTF8: ByteArray by lazy { name.encodeToByteArray() }
    public val nameLen: UShort by lazy { nameUTF8.size.toUShort() }
}
