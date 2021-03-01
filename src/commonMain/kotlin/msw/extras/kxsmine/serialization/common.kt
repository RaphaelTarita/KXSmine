package msw.extras.kxsmine.serialization

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.builtins.ByteArraySerializer
import kotlinx.serialization.builtins.IntArraySerializer
import kotlinx.serialization.builtins.LongArraySerializer
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.modules.SerializersModule

@ExperimentalSerializationApi
private val BYTEARRAY_SNAME = ByteArraySerializer().descriptor.serialName

@ExperimentalSerializationApi
private val INTARRAY_SNAME = IntArraySerializer().descriptor.serialName

@ExperimentalSerializationApi
private val LONGARRAY_SNAME = LongArraySerializer().descriptor.serialName


@ExperimentalSerializationApi
internal fun provideImpl(
    descriptor: SerialDescriptor,
    notifySuper: (NBTEncoder) -> Unit,
    rootName: String,
    serializersModule: SerializersModule
): NBTEncoder = when (descriptor.kind) {
    PrimitiveKind.BOOLEAN -> PrimitiveEncoder(notifySuper, rootName, serializersModule)
    PrimitiveKind.BYTE -> PrimitiveEncoder(notifySuper, rootName, serializersModule)
    PrimitiveKind.CHAR -> PrimitiveEncoder(notifySuper, rootName, serializersModule)
    PrimitiveKind.SHORT -> PrimitiveEncoder(notifySuper, rootName, serializersModule)
    PrimitiveKind.INT -> PrimitiveEncoder(notifySuper, rootName, serializersModule)
    PrimitiveKind.LONG -> PrimitiveEncoder(notifySuper, rootName, serializersModule)
    PrimitiveKind.FLOAT -> PrimitiveEncoder(notifySuper, rootName, serializersModule)
    PrimitiveKind.DOUBLE -> PrimitiveEncoder(notifySuper, rootName, serializersModule)
    PrimitiveKind.STRING -> PrimitiveEncoder(notifySuper, rootName, serializersModule)
    StructureKind.CLASS -> CompoundEncoder(notifySuper, rootName, serializersModule)
    StructureKind.LIST -> when (descriptor.serialName) {
        BYTEARRAY_SNAME -> ByteArrayEncoder(notifySuper, rootName, serializersModule)
        INTARRAY_SNAME -> IntArrayEncoder(notifySuper, rootName, serializersModule)
        LONGARRAY_SNAME -> LongArrayEncoder(notifySuper, rootName, serializersModule)
        else -> ListEncoder(notifySuper, rootName, serializersModule)
    }
    StructureKind.MAP -> CompoundEncoder(notifySuper, rootName, serializersModule)
    StructureKind.OBJECT -> CompoundEncoder(notifySuper, rootName, serializersModule)
    PolymorphicKind.SEALED -> CompoundEncoder(notifySuper, rootName, serializersModule)
    PolymorphicKind.OPEN -> CompoundEncoder(notifySuper, rootName, serializersModule)
    SerialKind.ENUM -> PrimitiveEncoder(notifySuper, rootName, serializersModule)
    SerialKind.CONTEXTUAL -> CompoundEncoder(notifySuper, rootName, serializersModule)
}

@ExperimentalSerializationApi
internal fun provideListImpl(
    descriptor: SerialDescriptor,
    notifySuper: (NBTEncoder) -> Unit,
    rootName: String,
    serializersModule: SerializersModule,
    collectionSize: Int
): NBTEncoder = when (descriptor.kind) {
    StructureKind.LIST -> when (descriptor.serialName) {
        BYTEARRAY_SNAME -> ByteArrayEncoder(notifySuper, rootName, serializersModule, collectionSize)
        INTARRAY_SNAME -> IntArrayEncoder(notifySuper, rootName, serializersModule, collectionSize)
        LONGARRAY_SNAME -> LongArrayEncoder(notifySuper, rootName, serializersModule, collectionSize)
        else -> ListEncoder(notifySuper, rootName, serializersModule, collectionSize)
    }
    else -> throw IllegalArgumentException("SerialKind was not list-like")
}