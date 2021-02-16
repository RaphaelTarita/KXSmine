package msw.extras.kxsmine.tree.decoding.payload

import msw.extras.kxsmine.tree.OffsetResult

public sealed class PayloadDecoder<T> {
    internal abstract val regexSignature: Regex
    public abstract fun decode(bytes: ByteArray, offset: Int): OffsetResult<T>
    public abstract fun decodeSNBT(str: String, offset: Int): OffsetResult<T>
}