package msw.extras.kxsmine.serialization

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encoding.AbstractEncoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule
import msw.extras.kxsmine.tree.node.tag.TagNode

@ExperimentalSerializationApi
public class PrimitiveEncoder(
    override val notifySuper: (sub: NBTEncoder) -> Unit = {},
    private val rootName: String = "",
    override val serializersModule: SerializersModule = EmptySerializersModule,
) : NBTEncoder, AbstractEncoder() {
    override val collector: MutableList<TagNode<*>> = SingleElementList()
}