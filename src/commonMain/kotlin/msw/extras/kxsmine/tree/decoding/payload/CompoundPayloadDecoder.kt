package msw.extras.kxsmine.tree.decoding.payload

import msw.extras.kxsmine.guessTagTypeFor
import msw.extras.kxsmine.indexOfMatching
import msw.extras.kxsmine.splitTopLevel
import msw.extras.kxsmine.tree.OffsetResult
import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.node.tag.TagNode

public object CompoundPayloadDecoder : PayloadDecoder<Collection<TagNode<*>>>() {
    override val regexSignature: Regex =
        "\\{(?:\\s*[a-zA-Z0-9\\s]+\\s*:\\s*(?:.|\\s)+)?(?:\\s*,\\s*[a-zA-Z0-9\\s]+\\s*:\\s*(?:.|\\s)+)*\\s*}".toRegex()

    override fun decode(bytes: ByteArray, offset: Int): OffsetResult<Collection<TagNode<*>>> {
        var iOffset = offset
        var currentType = TagType.fromID(bytes[iOffset])
        val result = ArrayList<TagNode<*>>(32)

        while (currentType != TagType.END) {
            val decoder = currentType.decoder
            val (res, off) = decoder.decode(bytes, iOffset)
            result.add(res)
            iOffset = off
            currentType = TagType.fromID(bytes[iOffset])
        }

        return OffsetResult(result, iOffset + 1)
    }

    override fun decodeSNBT(str: String, offset: Int): OffsetResult<Collection<TagNode<*>>> {
        val (startIndex, endIndex) = str.indexOfMatching('{', offset)
        val values = str.splitTopLevel(startIndex + 1, endIndex - 1, true)

        return OffsetResult(values.map {
            val type = guessTagTypeFor(it, true)
            type.decoder.decodeSNBT(it, 0).result
        }, endIndex + 1)
    }
}