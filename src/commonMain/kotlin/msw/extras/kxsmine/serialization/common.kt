package msw.extras.kxsmine.serialization

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.modules.SerializersModule


@ExperimentalSerializationApi
internal fun provideImpl(
    descriptor: SerialDescriptor,
    notifySuper: (NBTEncoder) -> Unit,
    rootName: String,
    serializersModule: SerializersModule
): NBTEncoder = when (descriptor.kind) {
    PrimitiveKind.BOOLEAN -> TODO()
    PrimitiveKind.BYTE -> TODO()
    PrimitiveKind.CHAR -> TODO()
    PrimitiveKind.SHORT -> TODO()
    PrimitiveKind.INT -> TODO()
    PrimitiveKind.LONG -> TODO()
    PrimitiveKind.FLOAT -> TODO()
    PrimitiveKind.DOUBLE -> TODO()
    PrimitiveKind.STRING -> TODO()
    StructureKind.CLASS -> TODO()
    StructureKind.LIST -> TODO()
    StructureKind.MAP -> TODO()
    StructureKind.OBJECT -> TODO()
    PolymorphicKind.SEALED -> TODO()
    PolymorphicKind.OPEN -> TODO()
    SerialKind.ENUM -> TODO()
    SerialKind.CONTEXTUAL -> TODO()
}