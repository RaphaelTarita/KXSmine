package msw.extras.kxsmine.serialization

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.AbstractEncoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule
import msw.extras.kxsmine.tree.node.payload.LongArrayPayloadNode
import msw.extras.kxsmine.tree.node.tag.LongArrayTagNode

@ExperimentalSerializationApi
public class LongArrayEncoder(
    override val notifySuper: (NBTEncoder) -> Unit = {},
    private val rootName: String = "",
    override val serializersModule: SerializersModule = EmptySerializersModule,
    collectionSize: Int = -1
) : NBTEncoder, AbstractEncoder() {
    private val fastCollector: LongArray? = if (collectionSize >= 0) LongArray(collectionSize) else null
    private var fcolIndex = 0
    override val collector: MutableList<Long> = mutableListOf()

    private fun add(data: Long) {
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
        return descriptor.getElementDescriptor(index).kind == PrimitiveKind.LONG
    }

    override fun encodeLong(value: Long) {
        add(value)
    }

    override fun extractRootTag(): LongArrayTagNode {
        return LongArrayTagNode(rootName, fastCollector ?: collector.toLongArray())
    }

    override fun extractRootPayload(): LongArrayPayloadNode {
        return LongArrayPayloadNode(fastCollector ?: collector.toLongArray())
    }
}