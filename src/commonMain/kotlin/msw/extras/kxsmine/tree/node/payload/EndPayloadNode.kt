package msw.extras.kxsmine.tree.node.payload

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.payload.EndPayloadDecoder
import msw.extras.kxsmine.tree.decoding.payload.PayloadDecoder
import msw.extras.kxsmine.tree.encoding.payload.EndPayloadEncoder
import msw.extras.kxsmine.tree.encoding.payload.PayloadEncoder

public object EndPayloadNode : PayloadNode<Unit>(Unit) {
    override val type: TagType = TagType.END
    override val encoder: PayloadEncoder<Unit> = EndPayloadEncoder
    override val decoder: PayloadDecoder<Unit> = EndPayloadDecoder
}