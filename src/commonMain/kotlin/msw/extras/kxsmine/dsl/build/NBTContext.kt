package msw.extras.kxsmine.dsl.build

import msw.extras.kxsmine.tree.node.payload.PayloadNode
import msw.extras.kxsmine.tree.node.tag.ByteArrayTagNode
import msw.extras.kxsmine.tree.node.tag.ByteTagNode
import msw.extras.kxsmine.tree.node.tag.CompoundTagNode
import msw.extras.kxsmine.tree.node.tag.DoubleTagNode
import msw.extras.kxsmine.tree.node.tag.EndTagNode
import msw.extras.kxsmine.tree.node.tag.FloatTagNode
import msw.extras.kxsmine.tree.node.tag.IntArrayTagNode
import msw.extras.kxsmine.tree.node.tag.IntTagNode
import msw.extras.kxsmine.tree.node.tag.ListTagNode
import msw.extras.kxsmine.tree.node.tag.LongArrayTagNode
import msw.extras.kxsmine.tree.node.tag.LongTagNode
import msw.extras.kxsmine.tree.node.tag.ShortTagNode
import msw.extras.kxsmine.tree.node.tag.StringTagNode
import msw.extras.kxsmine.tree.node.tag.TagNode

public class NBTContext : Collector<TagNode<*>> {
    private val drain: MutableList<TagNode<*>> = mutableListOf()

    private fun <T, N : TagNode<T>> addInternal(data: N): N {
        drain.add(data)
        return data
    }

    override fun add(data: TagNode<*>): TagNode<*> = addInternal(data)
    override fun addAll(data: Iterable<TagNode<*>>) {
        drain.addAll(data)
    }

    public fun end(): EndTagNode = addInternal(RootCreator.end())
    public fun byte(name: String, data: Byte): ByteTagNode = addInternal(RootCreator.byte(name, data))
    public fun short(name: String, data: Short): ShortTagNode = addInternal(RootCreator.short(name, data))
    public fun int(name: String, data: Int): IntTagNode = addInternal(RootCreator.int(name, data))
    public fun long(name: String, data: Long): LongTagNode = addInternal(RootCreator.long(name, data))
    public fun float(name: String, data: Float): FloatTagNode = addInternal(RootCreator.float(name, data))
    public fun double(name: String, data: Double): DoubleTagNode = addInternal(RootCreator.double(name, data))
    public fun bytearray(name: String, vararg data: Byte): ByteArrayTagNode = addInternal(RootCreator.bytearray(name, *data))
    public fun bytearray(name: String, data: List<Byte>): ByteArrayTagNode = addInternal(RootCreator.bytearray(name, data))
    public fun bytearray(name: String, builder: Collector<Byte>.() -> Unit): ByteArrayTagNode = addInternal(RootCreator.bytearray(name, builder))
    public fun intarray(name: String, vararg data: Int): IntArrayTagNode = addInternal(RootCreator.intarray(name, *data))
    public fun intarray(name: String, data: List<Int>): IntArrayTagNode = addInternal(RootCreator.intarray(name, data))
    public fun intarray(name: String, builder: Collector<Int>.() -> Unit): IntArrayTagNode = addInternal(RootCreator.intarray(name, builder))
    public fun longarray(name: String, vararg data: Long): LongArrayTagNode = addInternal(RootCreator.longarray(name, *data))
    public fun longarray(name: String, data: List<Long>): LongArrayTagNode = addInternal(RootCreator.longarray(name, data))
    public fun longarray(name: String, builder: Collector<Long>.() -> Unit): LongArrayTagNode = addInternal(RootCreator.longarray(name, builder))
    public fun string(name: String, data: String): StringTagNode = addInternal(RootCreator.string(name, data))
    public fun string(name: String, vararg data: Char): StringTagNode = addInternal(RootCreator.string(name, *data))
    public fun string(name: String, data: StringBuilder): StringTagNode = addInternal(RootCreator.string(name, data))
    public fun string(name: String, builder: Collector<Char>.() -> Unit): StringTagNode = addInternal(RootCreator.string(name, builder))
    public fun <T> list(name: String, data: List<PayloadNode<T>>): ListTagNode = addInternal(RootCreator.list(name, data))
    public fun <T> list(name: String, vararg data: PayloadNode<T>): ListTagNode = addInternal(RootCreator.list(name, *data))
    public fun list(name: String, builder: NBTPayloadContext.() -> Unit): ListTagNode = addInternal(RootCreator.list(name, builder))
    public fun compound(name: String, data: Collection<TagNode<*>>): CompoundTagNode = addInternal(RootCreator.compound(name, data))
    public fun compound(name: String, vararg data: TagNode<*>): CompoundTagNode = addInternal(RootCreator.compound(name, *data))
    public fun compound(name: String, builder: NBTContext.() -> Unit): CompoundTagNode = addInternal(RootCreator.compound(name, builder))

    override fun extract(): List<TagNode<*>> = drain

    public object RootCreator {
        public fun end(): EndTagNode = EndTagNode
        public fun byte(name: String, data: Byte): ByteTagNode = ByteTagNode(name, data)
        public fun short(name: String, data: Short): ShortTagNode = ShortTagNode(name, data)
        public fun int(name: String, data: Int): IntTagNode = IntTagNode(name, data)
        public fun long(name: String, data: Long): LongTagNode = LongTagNode(name, data)
        public fun float(name: String, data: Float): FloatTagNode = FloatTagNode(name, data)
        public fun double(name: String, data: Double): DoubleTagNode = DoubleTagNode(name, data)

        public fun bytearray(name: String, vararg data: Byte): ByteArrayTagNode = ByteArrayTagNode(name, data)
        public fun bytearray(name: String, data: List<Byte>): ByteArrayTagNode = ByteArrayTagNode(name, data.toByteArray())
        public fun bytearray(name: String, builder: Collector<Byte>.() -> Unit): ByteArrayTagNode {
            val collector = SimpleCollector<Byte>()
            collector.builder()
            return ByteArrayTagNode(name, collector.extract().toByteArray())
        }

        public fun intarray(name: String, vararg data: Int): IntArrayTagNode = IntArrayTagNode(name, data)
        public fun intarray(name: String, data: List<Int>): IntArrayTagNode = IntArrayTagNode(name, data.toIntArray())
        public fun intarray(name: String, builder: Collector<Int>.() -> Unit): IntArrayTagNode {
            val collector = SimpleCollector<Int>()
            collector.builder()
            return IntArrayTagNode(name, collector.extract().toIntArray())
        }

        public fun longarray(name: String, vararg data: Long): LongArrayTagNode = LongArrayTagNode(name, data)
        public fun longarray(name: String, data: List<Long>): LongArrayTagNode = LongArrayTagNode(name, data.toLongArray())
        public fun longarray(name: String, builder: Collector<Long>.() -> Unit): LongArrayTagNode {
            val collector = SimpleCollector<Long>()
            collector.builder()
            return LongArrayTagNode(name, collector.extract().toLongArray())
        }

        public fun string(name: String, data: String): StringTagNode = StringTagNode(name, data)
        public fun string(name: String, vararg data: Char): StringTagNode = StringTagNode(name, data.concatToString())
        public fun string(name: String, data: StringBuilder): StringTagNode = StringTagNode(name, data.toString())
        public fun string(name: String, builder: Collector<Char>.() -> Unit): StringTagNode {
            val collector = SimpleCollector<Char>()
            collector.builder()
            return StringTagNode(name, collector.extract().joinToString(""))
        }

        public fun <T> list(name: String, data: List<PayloadNode<T>>): ListTagNode = ListTagNode(name, data)
        public fun <T> list(name: String, vararg data: PayloadNode<T>): ListTagNode = ListTagNode(name, data.toList())
        public fun list(name: String, builder: NBTPayloadContext.() -> Unit): ListTagNode {
            val collector = NBTPayloadContext()
            collector.builder()
            return ListTagNode(name, collector.extract())
        }

        public fun compound(name: String, data: Collection<TagNode<*>>): CompoundTagNode = CompoundTagNode(name, data)
        public fun compound(name: String, vararg data: TagNode<*>): CompoundTagNode = CompoundTagNode(name, data.toList())
        public fun compound(name: String, builder: NBTContext.() -> Unit): CompoundTagNode {
            val collector = NBTContext()
            collector.builder()
            return CompoundTagNode(name, collector.extract())
        }
    }
}