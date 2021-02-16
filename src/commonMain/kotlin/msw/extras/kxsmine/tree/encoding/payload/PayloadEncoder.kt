package msw.extras.kxsmine.tree.encoding.payload

import msw.extras.kxsmine.tree.node.payload.PayloadNode

public sealed class PayloadEncoder<T> {
    public abstract fun encode(node: PayloadNode<T>): ByteArray
    public abstract fun encodeSNBT(node: PayloadNode<T>): String
}
