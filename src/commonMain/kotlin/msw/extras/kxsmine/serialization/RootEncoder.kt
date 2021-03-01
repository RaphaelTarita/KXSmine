package msw.extras.kxsmine.serialization

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule
import msw.extras.kxsmine.tree.node.payload.BytePayloadNode
import msw.extras.kxsmine.tree.node.payload.DoublePayloadNode
import msw.extras.kxsmine.tree.node.payload.EndPayloadNode
import msw.extras.kxsmine.tree.node.payload.FloatPayloadNode
import msw.extras.kxsmine.tree.node.payload.IntPayloadNode
import msw.extras.kxsmine.tree.node.payload.LongPayloadNode
import msw.extras.kxsmine.tree.node.payload.PayloadNode
import msw.extras.kxsmine.tree.node.payload.ShortPayloadNode
import msw.extras.kxsmine.tree.node.payload.StringPayloadNode

@ExperimentalSerializationApi
public class RootEncoder(
    override val serializersModule: SerializersModule = EmptySerializersModule
) : Encoder {
    private var result: PayloadNode<*>? = null
    override fun beginStructure(descriptor: SerialDescriptor): CompositeEncoder {
        return provideImpl(
            descriptor,
            { result = it.extractRootPayload() },
            "",
            serializersModule
        )
    }

    override fun beginCollection(descriptor: SerialDescriptor, collectionSize: Int): CompositeEncoder {
        return provideListImpl(
            descriptor,
            { result = it.extractRootPayload() },
            "",
            serializersModule,
            collectionSize
        )
    }

    override fun encodeInline(inlineDescriptor: SerialDescriptor): Encoder {
        return PrimitiveEncoder(
            { result = it.extractRootPayload() },
            "",
            serializersModule
        )
    }

    override fun encodeByte(value: Byte) {
        result = BytePayloadNode(value)
    }

    override fun encodeBoolean(value: Boolean) {
        // TODO: Control encoding details (byte / string) with config
        result = BytePayloadNode(if (value) 1 else 0)
    }

    override fun encodeShort(value: Short) {
        result = ShortPayloadNode(value)
    }

    override fun encodeInt(value: Int) {
        result = IntPayloadNode(value)
    }

    override fun encodeLong(value: Long) {
        result = LongPayloadNode(value)
    }

    override fun encodeFloat(value: Float) {
        result = FloatPayloadNode(value)
    }

    override fun encodeDouble(value: Double) {
        result = DoublePayloadNode(value)
    }

    override fun encodeString(value: String) {
        result = StringPayloadNode(value)
    }

    override fun encodeChar(value: Char) {
        // TODO: Control encoding details (byte / string) with config
        result = StringPayloadNode(value.toString())
    }

    override fun encodeEnum(enumDescriptor: SerialDescriptor, index: Int) {
        // TODO: Control encoding details (byte / short / int / long / dynamic / string) with config
        result = BytePayloadNode(index.toByte())
    }

    override fun encodeNull() {
        result = EndPayloadNode
    }

    public fun getNBT(): ByteArray {
        return result?.encode() ?: byteArrayOf()
    }

    public fun getSNBT(): String {
        return result?.encodeSNBT() ?: ""
    }
}