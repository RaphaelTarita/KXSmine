package msw.extras.kxsmine.tree.decoding.tag

import msw.extras.kxsmine.tree.NBTDecodingException
import msw.extras.kxsmine.tree.OffsetResult
import msw.extras.kxsmine.tree.SNBTDecodingException
import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.payload.PayloadDecoder
import msw.extras.kxsmine.tree.node.tag.TagNode

public sealed class TagDecoder<T> {
    internal companion object {
        internal val snbtRegex = "([a-zA-Z0-9\\s]+):(.*)".toRegex()
    }

    public abstract val type: TagType
    public abstract val payloadDecoder: PayloadDecoder<T>

    public open fun decode(bytes: ByteArray, offset: Int): OffsetResult<out TagNode<T>> {
        val (name, intermediateOffset) = getName(bytes, offset)
        val (data, newOffset) = payloadDecoder.decode(bytes, intermediateOffset)
        return OffsetResult(nodeSupplier(name, data), newOffset)
    }

    public open fun decodeSNBT(str: String, offset: Int): OffsetResult<out TagNode<T>> {
        val res = snbtRegex.find(str, offset) ?: throw SNBTDecodingException(type, str.substring(offset))
        val (data, newOffset) = payloadDecoder.decodeSNBT(res.groupValues[2], 0)
        return OffsetResult(nodeSupplier(res.groupValues[1], data), res.range.first + newOffset)
    }

    private fun getName(bytes: ByteArray, offset: Int): OffsetResult<String> {
        if (bytes[offset] != type.id) throw NBTDecodingException(type, bytes, offset)
        val stringLen = (bytes[offset + 1].toInt() shl 8) or bytes[offset + 2].toInt()
        val intermediateOffset = offset + 3 + stringLen
        return OffsetResult(
            bytes.sliceArray(offset + 3 until intermediateOffset).decodeToString(),
            intermediateOffset
        )
    }

    internal abstract val nodeSupplier: (String, T) -> TagNode<T>
}
