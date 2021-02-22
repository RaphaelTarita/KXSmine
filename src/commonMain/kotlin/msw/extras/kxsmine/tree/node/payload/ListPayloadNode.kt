package msw.extras.kxsmine.tree.node.payload

import msw.extras.kxsmine.guessTagTypeFor
import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.decoding.payload.ListPayloadDecoder
import msw.extras.kxsmine.tree.decoding.payload.PayloadDecoder
import msw.extras.kxsmine.tree.encoding.payload.ListPayloadEncoder
import msw.extras.kxsmine.tree.encoding.payload.PayloadEncoder

public class ListPayloadNode(data: List<PayloadNode<*>>) : PayloadNode<List<PayloadNode<*>>>(data) {
    override val type: TagType = TagType.LIST
    override val encoder: PayloadEncoder<List<PayloadNode<*>>> = ListPayloadEncoder
    override val decoder: PayloadDecoder<List<PayloadNode<*>>> = ListPayloadDecoder

    public companion object {
        public inline fun <reified T> listPayloadNode(data: List<T>): ListPayloadNode {
            val payloadConstructor = guessTagTypeFor<T>().elementType.ctorGeneric
            return ListPayloadNode(data.map { payloadConstructor(it) })
        }
    }
}