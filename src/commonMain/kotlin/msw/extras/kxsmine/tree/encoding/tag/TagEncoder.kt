package msw.extras.kxsmine.tree.encoding.tag

import msw.extras.kxsmine.lsb
import msw.extras.kxsmine.msb
import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.encoding.payload.PayloadEncoder
import msw.extras.kxsmine.tree.node.tag.TagNode

public sealed class TagEncoder<T> {
    public abstract val type: TagType
    public abstract val payloadEncoder: PayloadEncoder<T>
    public open fun encode(node: TagNode<T>): ByteArray = idLengthAndName(node) + payloadEncoder.encode(node.payload)
    public open fun encodeSNBT(node: TagNode<T>): String = "${node.name}:${payloadEncoder.encodeSNBT(node.payload)}"

    private fun idLengthAndName(node: TagNode<T>): ByteArray {
        val arr = ByteArray(node.nameLen.toInt() + 3)
        arr[0] = node.type.id
        arr[1] = node.nameLen.msb()
        arr[2] = node.nameLen.lsb()
        for ((i, byte) in node.nameUTF8.withIndex()) {
            arr[i + 3] = byte
        }
        return arr
    }
}