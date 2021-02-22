package msw.extras.kxsmine.serialization

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.modules.SerializersModule
import msw.extras.kxsmine.dsl.build.NBTContext

@ExperimentalSerializationApi
public class NBTCompositeEncoder(
    override val serializersModule: SerializersModule
) : CompositeEncoder {
    private val context: NBTContext = NBTContext()

    private fun getName(descriptor: SerialDescriptor, index: Int): String {
        return descriptor.getElementName(index)
    }

    override fun encodeBooleanElement(descriptor: SerialDescriptor, index: Int, value: Boolean) {
        context.byte(getName(descriptor, index), if (value) 1 else 0)
    }

    override fun encodeByteElement(descriptor: SerialDescriptor, index: Int, value: Byte) {
        context.byte(getName(descriptor, index), value)
    }

    override fun encodeCharElement(descriptor: SerialDescriptor, index: Int, value: Char) {
        context.string(getName(descriptor, index), value.toString())
    }

    override fun encodeDoubleElement(descriptor: SerialDescriptor, index: Int, value: Double) {
        context.double(getName(descriptor, index), value)
    }

    override fun encodeFloatElement(descriptor: SerialDescriptor, index: Int, value: Float) {
        context.float(getName(descriptor, index), value)
    }

    @ExperimentalSerializationApi
    override fun encodeInlineElement(descriptor: SerialDescriptor, index: Int): Encoder {
        TODO("Not yet implemented")
    }

    override fun encodeIntElement(descriptor: SerialDescriptor, index: Int, value: Int) {
        context.int(getName(descriptor, index), value)
    }

    override fun encodeLongElement(descriptor: SerialDescriptor, index: Int, value: Long) {
        context.long(getName(descriptor, index), value)
    }

    @ExperimentalSerializationApi
    override fun <T : Any> encodeNullableSerializableElement(
        descriptor: SerialDescriptor,
        index: Int,
        serializer: SerializationStrategy<T>,
        value: T?
    ) {
        TODO("Not yet implemented")
    }

    override fun <T> encodeSerializableElement(descriptor: SerialDescriptor, index: Int, serializer: SerializationStrategy<T>, value: T) {
        TODO("Not yet implemented")
    }

    override fun encodeShortElement(descriptor: SerialDescriptor, index: Int, value: Short) {
        context.short(getName(descriptor, index), value)
    }

    override fun encodeStringElement(descriptor: SerialDescriptor, index: Int, value: String) {
        context.string(getName(descriptor, index), value)
    }

    override fun endStructure(descriptor: SerialDescriptor) {
        TODO("Not yet implemented")
    }
}