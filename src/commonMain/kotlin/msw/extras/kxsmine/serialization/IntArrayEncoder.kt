package msw.extras.kxsmine.serialization

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.AbstractEncoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule
import msw.extras.kxsmine.tree.node.payload.IntArrayPayloadNode
import msw.extras.kxsmine.tree.node.tag.IntArrayTagNode

@ExperimentalSerializationApi
public class IntArrayEncoder(
    override val notifySuper: (NBTEncoder) -> Unit = {},
    private val rootName: String = "",
    override val serializersModule: SerializersModule = EmptySerializersModule,
    collectionSize: Int = -1
) : NBTEncoder, AbstractEncoder() {
    private val fastCollector: IntArray? = if (collectionSize >= 0) IntArray(collectionSize) else null
    private var fcolIndex = 0
    override val collector: MutableList<Int> = mutableListOf()

    private fun add(data: Int) {
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
        return descriptor.getElementDescriptor(index).kind == PrimitiveKind.INT
    }

    override fun encodeInt(value: Int) {
        add(value)
    }

    override fun extractRootTag(): IntArrayTagNode {
        return IntArrayTagNode(rootName, fastCollector ?: collector.toIntArray())
    }

    override fun extractRootPayload(): IntArrayPayloadNode {
        return IntArrayPayloadNode(fastCollector ?: collector.toIntArray())
    }
}