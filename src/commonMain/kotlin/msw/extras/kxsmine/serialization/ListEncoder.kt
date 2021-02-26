package msw.extras.kxsmine.serialization

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.AbstractEncoder
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule
import msw.extras.kxsmine.tree.node.payload.BytePayloadNode
import msw.extras.kxsmine.tree.node.payload.DoublePayloadNode
import msw.extras.kxsmine.tree.node.payload.EndPayloadNode
import msw.extras.kxsmine.tree.node.payload.FloatPayloadNode
import msw.extras.kxsmine.tree.node.payload.IntPayloadNode
import msw.extras.kxsmine.tree.node.payload.ListPayloadNode
import msw.extras.kxsmine.tree.node.payload.LongPayloadNode
import msw.extras.kxsmine.tree.node.payload.PayloadNode
import msw.extras.kxsmine.tree.node.payload.ShortPayloadNode
import msw.extras.kxsmine.tree.node.payload.StringPayloadNode
import msw.extras.kxsmine.tree.node.tag.ListTagNode

@ExperimentalSerializationApi
public class ListEncoder(
    override val notifySuper: (NBTEncoder) -> Unit = {},
    private val rootName: String = "",
    override val serializersModule: SerializersModule = EmptySerializersModule,
) : NBTEncoder, AbstractEncoder() {
    override val collector: MutableList<PayloadNode<*>> = mutableListOf()

    override fun beginStructure(descriptor: SerialDescriptor): CompositeEncoder {
        return provideImpl(
            descriptor,
            { collector.add(it.extractRootPayload()) },
            "",
            serializersModule
        )
    }

    override fun encodeByte(value: Byte) {
        collector.add(BytePayloadNode(value))
    }

    override fun encodeBoolean(value: Boolean) {
        // TODO: Control encoding details (byte / string) with config
        collector.add(BytePayloadNode(if (value) 1 else 0))
    }

    override fun encodeShort(value: Short) {
        collector.add(ShortPayloadNode(value))
    }

    override fun encodeInt(value: Int) {
        collector.add(IntPayloadNode(value))
    }

    override fun encodeLong(value: Long) {
        collector.add(LongPayloadNode(value))
    }

    override fun encodeFloat(value: Float) {
        collector.add(FloatPayloadNode(value))
    }

    override fun encodeDouble(value: Double) {
        collector.add(DoublePayloadNode(value))
    }

    override fun encodeString(value: String) {
        collector.add(StringPayloadNode(value))
    }

    override fun encodeChar(value: Char) {
        // TODO: Control encoding details (byte / string) with config
        collector.add(StringPayloadNode(value.toString()))
    }

    override fun encodeEnum(enumDescriptor: SerialDescriptor, index: Int) {
        // TODO: Control encoding details (byte / short / int / long / dynamic / string) with config
        collector.add(BytePayloadNode(index.toByte()))
    }

    override fun encodeNull() {
        collector.add(EndPayloadNode)
    }

    override fun extractRootTag(): ListTagNode {
        return ListTagNode(rootName, collector)
    }

    override fun extractRootPayload(): PayloadNode<*> {
        return ListPayloadNode(collector)
    }
}