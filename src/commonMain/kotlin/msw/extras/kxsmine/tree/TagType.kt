package msw.extras.kxsmine.tree

import msw.extras.kxsmine.dsl.build.elem.ByteArrays
import msw.extras.kxsmine.dsl.build.elem.Bytes
import msw.extras.kxsmine.dsl.build.elem.Compounds
import msw.extras.kxsmine.dsl.build.elem.Doubles
import msw.extras.kxsmine.dsl.build.elem.ElementType
import msw.extras.kxsmine.dsl.build.elem.Ends
import msw.extras.kxsmine.dsl.build.elem.Floats
import msw.extras.kxsmine.dsl.build.elem.IntArrays
import msw.extras.kxsmine.dsl.build.elem.Ints
import msw.extras.kxsmine.dsl.build.elem.Lists
import msw.extras.kxsmine.dsl.build.elem.LongArrays
import msw.extras.kxsmine.dsl.build.elem.Longs
import msw.extras.kxsmine.dsl.build.elem.Shorts
import msw.extras.kxsmine.dsl.build.elem.Strings
import msw.extras.kxsmine.tree.decoding.tag.ByteArrayTagDecoder
import msw.extras.kxsmine.tree.decoding.tag.ByteTagDecoder
import msw.extras.kxsmine.tree.decoding.tag.CompoundTagDecoder
import msw.extras.kxsmine.tree.decoding.tag.DoubleTagDecoder
import msw.extras.kxsmine.tree.decoding.tag.EndTagDecoder
import msw.extras.kxsmine.tree.decoding.tag.FloatTagDecoder
import msw.extras.kxsmine.tree.decoding.tag.IntArrayTagDecoder
import msw.extras.kxsmine.tree.decoding.tag.IntTagDecoder
import msw.extras.kxsmine.tree.decoding.tag.ListTagDecoder
import msw.extras.kxsmine.tree.decoding.tag.LongArrayTagDecoder
import msw.extras.kxsmine.tree.decoding.tag.LongTagDecoder
import msw.extras.kxsmine.tree.decoding.tag.ShortTagDecoder
import msw.extras.kxsmine.tree.decoding.tag.StringTagDecoder
import msw.extras.kxsmine.tree.decoding.tag.TagDecoder
import msw.extras.kxsmine.tree.encoding.tag.ByteArrayTagEncoder
import msw.extras.kxsmine.tree.encoding.tag.ByteTagEncoder
import msw.extras.kxsmine.tree.encoding.tag.CompoundTagEncoder
import msw.extras.kxsmine.tree.encoding.tag.DoubleTagEncoder
import msw.extras.kxsmine.tree.encoding.tag.EndTagEncoder
import msw.extras.kxsmine.tree.encoding.tag.FloatTagEncoder
import msw.extras.kxsmine.tree.encoding.tag.IntArrayTagEncoder
import msw.extras.kxsmine.tree.encoding.tag.IntTagEncoder
import msw.extras.kxsmine.tree.encoding.tag.ListTagEncoder
import msw.extras.kxsmine.tree.encoding.tag.LongArrayTagEncoder
import msw.extras.kxsmine.tree.encoding.tag.LongTagEncoder
import msw.extras.kxsmine.tree.encoding.tag.ShortTagEncoder
import msw.extras.kxsmine.tree.encoding.tag.StringTagEncoder
import msw.extras.kxsmine.tree.encoding.tag.TagEncoder

public enum class TagType(
    public val id: Byte,
    public val displayName: String,
    internal val heuristicSize: Int,
) {
    END(0, "TAG_End", 0),
    BYTE(1, "TAG_Byte", 1),
    SHORT(2, "TAG_Short", 2),
    INT(3, "TAG_Int", 4),
    LONG(4, "TAG_Long", 8),
    FLOAT(5, "TAG_Float", 4),
    DOUBLE(6, "TAG_Double", 8),
    BYTEARRAY(7, "TAG_Byte_Array", 128),
    STRING(8, "TAG_String", 128),
    LIST(9, "TAG_List", 256),
    COMPOUND(10, "TAG_Compound", 1024),
    INTARRAY(11, "TAG_Int_Array", 128 * 4),
    LONGARRAY(12, "TAG_Long_Array", 128 * 8);

    // needed to prevent circular references in constant initializers
    public val encoder: TagEncoder<*>
        get() = when (this) {
            END -> EndTagEncoder
            BYTE -> ByteTagEncoder
            SHORT -> ShortTagEncoder
            INT -> IntTagEncoder
            LONG -> LongTagEncoder
            FLOAT -> FloatTagEncoder
            DOUBLE -> DoubleTagEncoder
            BYTEARRAY -> ByteArrayTagEncoder
            STRING -> StringTagEncoder
            LIST -> ListTagEncoder
            COMPOUND -> CompoundTagEncoder
            INTARRAY -> IntArrayTagEncoder
            LONGARRAY -> LongArrayTagEncoder
        }

    public val decoder: TagDecoder<*>
        get() = when (this) {
            END -> EndTagDecoder
            BYTE -> ByteTagDecoder
            SHORT -> ShortTagDecoder
            INT -> IntTagDecoder
            LONG -> LongTagDecoder
            FLOAT -> FloatTagDecoder
            DOUBLE -> DoubleTagDecoder
            BYTEARRAY -> ByteArrayTagDecoder
            STRING -> StringTagDecoder
            LIST -> ListTagDecoder
            COMPOUND -> CompoundTagDecoder
            INTARRAY -> IntArrayTagDecoder
            LONGARRAY -> LongArrayTagDecoder
        }

    public val elementType: ElementType<*>
        get() = when (this) {
            END -> Ends
            BYTE -> Bytes
            SHORT -> Shorts
            INT -> Ints
            LONG -> Longs
            FLOAT -> Floats
            DOUBLE -> Doubles
            BYTEARRAY -> ByteArrays
            STRING -> Strings
            LIST -> Lists
            COMPOUND -> Compounds
            INTARRAY -> IntArrays
            LONGARRAY -> LongArrays
        }

    public override fun toString(): String {
        return displayName
    }

    public companion object {
        public fun fromID(id: Byte): TagType {
            for (value in values()) {
                if (value.id == id) return value
            }
            throw NBTDecodingException("Unknown Tag type: id '$id'")
        }
    }
}