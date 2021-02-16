package msw.extras.kxsmine.tree.decoding.payload

import msw.extras.kxsmine.indexOfExcluding
import msw.extras.kxsmine.tree.OffsetResult
import msw.extras.kxsmine.unescape

public object StringPayloadDecoder : PayloadDecoder<String>() {
    override val regexSignature: Regex = "(?:[a-zA-Z0-9\\s]+|\".*\"|\'.*\')".toRegex()
    private val cleanChars = charArrayOf(
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
        'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
        'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
        'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F',
        'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
        'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
        'W', 'X', 'Y', 'Z', '0', '1', '2', '3',
        '4', '5', '6', '7', '8', '9', ' '
    )

    override fun decode(bytes: ByteArray, offset: Int): OffsetResult<String> {
        val len = (bytes[offset].toInt() shl 8) or bytes[offset + 1].toInt()
        return OffsetResult(
            bytes.decodeToString(offset + 2, offset + len + 2),
            offset + len + 2
        )
    }

    override fun decodeSNBT(str: String, offset: Int): OffsetResult<String> {
        return when (str[offset]) {
            '\"' -> {
                val endIndex = str.indexOfExcluding('\"', "\\", startIndex = offset + 1)
                OffsetResult(
                    str.substring(offset + 1, endIndex).unescape('\"'),
                    endIndex + 1
                )
            }
            '\'' -> {
                val endIndex = str.indexOfExcluding('\'', "\\", startIndex = offset + 1)
                OffsetResult(
                    str.substring(offset + 1, endIndex).unescape('\''),
                    endIndex + 1
                )
            }
            else -> {
                val dirty = str.indexOfFirst { it !in cleanChars }
                val endIndex = if (dirty != -1) dirty else str.length
                OffsetResult(
                    str.substring(offset, endIndex),
                    endIndex + 1
                )
            }
        }
    }
}