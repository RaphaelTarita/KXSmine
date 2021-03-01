package msw.extras.kxsmine.serialization

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.AbstractEncoder
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule
import msw.extras.kxsmine.exc.NBTSerializationException
import msw.extras.kxsmine.tree.node.payload.PayloadNode
import msw.extras.kxsmine.tree.node.tag.ByteTagNode
import msw.extras.kxsmine.tree.node.tag.DoubleTagNode
import msw.extras.kxsmine.tree.node.tag.EndTagNode
import msw.extras.kxsmine.tree.node.tag.FloatTagNode
import msw.extras.kxsmine.tree.node.tag.IntTagNode
import msw.extras.kxsmine.tree.node.tag.LongTagNode
import msw.extras.kxsmine.tree.node.tag.ShortTagNode
import msw.extras.kxsmine.tree.node.tag.StringTagNode
import msw.extras.kxsmine.tree.node.tag.TagNode

@ExperimentalSerializationApi
public class PrimitiveEncoder(
    override val notifySuper: (sub: NBTEncoder) -> Unit = {},
    private val rootName: String = "",
    override val serializersModule: SerializersModule = EmptySerializersModule,
) : NBTEncoder, AbstractEncoder() {
    private val _collector = SingleElementList<TagNode<*>>()
    override val collector: MutableList<TagNode<*>>
        get() = _collector

    override fun beginStructure(descriptor: SerialDescriptor): CompositeEncoder {
        throw NBTSerializationException("Cannot begin structure from Primitive Encoder")
    }

    override fun endStructure(descriptor: SerialDescriptor) {
        notifySuper(this)
    }

    override fun encodeByte(value: Byte) {
        collector.add(ByteTagNode(rootName, value))
        notifySuper(this)
    }

    override fun encodeBoolean(value: Boolean) {
        // TODO: Control encoding details (byte / string) with config
        collector.add(ByteTagNode(rootName, if (value) 1 else 0))
        notifySuper(this)
    }

    override fun encodeShort(value: Short) {
        collector.add(ShortTagNode(rootName, value))
        notifySuper(this)
    }

    override fun encodeInt(value: Int) {
        collector.add(IntTagNode(rootName, value))
        notifySuper(this)
    }

    override fun encodeLong(value: Long) {
        collector.add(LongTagNode(rootName, value))
        notifySuper(this)
    }

    override fun encodeFloat(value: Float) {
        collector.add(FloatTagNode(rootName, value))
        notifySuper(this)
    }

    override fun encodeDouble(value: Double) {
        collector.add(DoubleTagNode(rootName, value))
        notifySuper(this)
    }

    override fun encodeString(value: String) {
        collector.add(StringTagNode(rootName, value))
        notifySuper(this)
    }

    override fun encodeChar(value: Char) {
        // TODO: Control encoding details (byte / string) with config
        collector.add(StringTagNode(rootName, value.toString()))
        notifySuper(this)
    }

    override fun encodeEnum(enumDescriptor: SerialDescriptor, index: Int) {
        // TODO: Control encoding details (byte / short / int / long / dynamic / string) with config
        collector.add(ByteTagNode(rootName, index.toByte()))
        notifySuper(this)
    }

    override fun encodeNull() {
        collector.add(EndTagNode)
        notifySuper(this)
    }

    override fun encodeElement(descriptor: SerialDescriptor, index: Int): Boolean {
        return descriptor.getElementDescriptor(index).kind is PrimitiveKind && collector.isEmpty()
    }

    override fun extractRootTag(): TagNode<*> {
        return _collector.element()
    }

    override fun extractRootPayload(): PayloadNode<*> {
        return _collector.element().payload
    }
}