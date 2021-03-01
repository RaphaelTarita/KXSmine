package msw.extras.kxsmine.serialization

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.encoding.AbstractEncoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule
import msw.extras.kxsmine.tree.node.payload.ByteArrayPayloadNode
import msw.extras.kxsmine.tree.node.tag.ByteArrayTagNode

// TODO: Allow Char Arrays if specified in config

@ExperimentalSerializationApi
public class ByteArrayEncoder(
    override val notifySuper: (NBTEncoder) -> Unit = {},
    private val rootName: String = "",
    override val serializersModule: SerializersModule = EmptySerializersModule,
    collectionSize: Int = -1
) : NBTEncoder, AbstractEncoder() {
    private val fastCollector: ByteArray? = if (collectionSize >= 0) ByteArray(collectionSize) else null
    private var fcolIndex = 0
    override val collector: MutableList<Byte> = mutableListOf()

    private fun add(data: Byte) {
        if (fastCollector != null) {
            fastCollector[fcolIndex++] = data
        } else {
            collector.add(data)
        }
    }

    override fun endStructure(descriptor: SerialDescriptor) {
        notifySuper(this)
    }

    override fun encodeElement(descriptor: SerialDescriptor, index: Int): Boolean {
        val kind = descriptor.getElementDescriptor(index).kind
        return kind == PrimitiveKind.BYTE ||
                kind == PrimitiveKind.BOOLEAN ||
                kind == SerialKind.ENUM
    }

    override fun encodeByte(value: Byte) {
        add(value)
    }

    override fun encodeBoolean(value: Boolean) {
        add(if (value) 1 else 0)
    }

    override fun encodeEnum(enumDescriptor: SerialDescriptor, index: Int) {
        // TODO: Control actual enum encoded format with config
        add(index.toByte())
    }

    override fun extractRootTag(): ByteArrayTagNode {
        return ByteArrayTagNode(rootName, fastCollector ?: collector.toByteArray())
    }

    override fun extractRootPayload(): ByteArrayPayloadNode {
        return ByteArrayPayloadNode(fastCollector ?: collector.toByteArray())
    }
}