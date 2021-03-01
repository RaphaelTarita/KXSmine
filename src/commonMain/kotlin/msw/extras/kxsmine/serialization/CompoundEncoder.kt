package msw.extras.kxsmine.serialization

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.AbstractEncoder
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule
import msw.extras.kxsmine.tree.node.payload.CompoundPayloadNode
import msw.extras.kxsmine.tree.node.tag.ByteTagNode
import msw.extras.kxsmine.tree.node.tag.CompoundTagNode
import msw.extras.kxsmine.tree.node.tag.DoubleTagNode
import msw.extras.kxsmine.tree.node.tag.EndTagNode
import msw.extras.kxsmine.tree.node.tag.FloatTagNode
import msw.extras.kxsmine.tree.node.tag.IntTagNode
import msw.extras.kxsmine.tree.node.tag.LongTagNode
import msw.extras.kxsmine.tree.node.tag.ShortTagNode
import msw.extras.kxsmine.tree.node.tag.StringTagNode
import msw.extras.kxsmine.tree.node.tag.TagNode

@ExperimentalSerializationApi
public class CompoundEncoder(
    override val notifySuper: (sub: NBTEncoder) -> Unit = {},
    private val rootName: String = "",
    override val serializersModule: SerializersModule = EmptySerializersModule,
) : NBTEncoder, AbstractEncoder() {
    override val collector: MutableList<TagNode<*>> = mutableListOf()
    private val nextName = ConsumableString()

    override fun beginStructure(descriptor: SerialDescriptor): CompositeEncoder {
        return provideImpl(
            descriptor,
            { collector.add(it.extractRootTag()) },
            nextName.consume(),
            serializersModule
        )
    }

    override fun beginCollection(descriptor: SerialDescriptor, collectionSize: Int): CompositeEncoder {
        return provideListImpl(
            descriptor,
            { collector.add(it.extractRootTag()) },
            nextName.consume(),
            serializersModule,
            collectionSize
        )
    }

    override fun endStructure(descriptor: SerialDescriptor) {
        notifySuper(this)
    }

    override fun encodeInline(inlineDescriptor: SerialDescriptor): Encoder {
        return PrimitiveEncoder(
            { collector.add(it.extractRootTag()) },
            nextName.consume(),
            serializersModule
        )
    }

    override fun encodeByte(value: Byte) {
        collector.add(ByteTagNode(nextName.consume(), value))
    }

    override fun encodeBoolean(value: Boolean) {
        // TODO: Control encoding details (byte / string) with config
        collector.add(ByteTagNode(nextName.consume(), if (value) 1 else 0))
    }

    override fun encodeShort(value: Short) {
        collector.add(ShortTagNode(nextName.consume(), value))
    }

    override fun encodeInt(value: Int) {
        collector.add(IntTagNode(nextName.consume(), value))
    }

    override fun encodeLong(value: Long) {
        collector.add(LongTagNode(nextName.consume(), value))
    }

    override fun encodeFloat(value: Float) {
        collector.add(FloatTagNode(nextName.consume(), value))
    }

    override fun encodeDouble(value: Double) {
        collector.add(DoubleTagNode(nextName.consume(), value))
    }

    override fun encodeString(value: String) {
        collector.add(StringTagNode(nextName.consume(), value))
    }

    override fun encodeChar(value: Char) {
        // TODO: Control encoding details (byte / string) with config
        collector.add(StringTagNode(nextName.consume(), value.toString()))
    }

    override fun encodeEnum(enumDescriptor: SerialDescriptor, index: Int) {
        // TODO: Control encoding details (byte / short / int / long / dynamic / string) with config
        collector.add(ByteTagNode(nextName.consume(), index.toByte()))
    }

    override fun encodeNull() {
        collector.add(EndTagNode)
    }

    override fun encodeElement(descriptor: SerialDescriptor, index: Int): Boolean {
        nextName.supply(descriptor.getElementName(index))
        return true
    }

    override fun extractRootTag(): CompoundTagNode {
        return CompoundTagNode(rootName, collector)
    }

    override fun extractRootPayload(): CompoundPayloadNode {
        return CompoundPayloadNode(collector)
    }
}