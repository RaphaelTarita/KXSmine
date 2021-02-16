package msw.extras.kxsmine.tree.encoding.payload

import msw.extras.kxsmine.escape
import msw.extras.kxsmine.lsb
import msw.extras.kxsmine.msb
import msw.extras.kxsmine.tree.node.payload.PayloadNode

public object StringPayloadEncoder : PayloadEncoder<String>() {
    private val cleanStringRegex = "[a-zA-Z0-9]*".toRegex()
    override fun encode(node: PayloadNode<String>): ByteArray {
        val bytes = node.data.encodeToByteArray()
        val len = bytes.size.toUShort()
        val arr = ByteArray(len.toInt() + 2)

        arr[0] = len.msb()
        arr[1] = len.lsb()
        for ((i, byte) in bytes.withIndex()) {
            arr[i + 2] = byte
        }

        return arr
    }

    override fun encodeSNBT(node: PayloadNode<String>): String {
        return if (node.data.matches(cleanStringRegex)) {
            node.data
        } else {
            "\"${node.data.escape('\"')}\""
        }
    }
}