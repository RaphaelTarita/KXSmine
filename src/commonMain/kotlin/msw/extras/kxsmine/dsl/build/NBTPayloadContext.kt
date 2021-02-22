package msw.extras.kxsmine.dsl.build

import msw.extras.kxsmine.tree.node.payload.ByteArrayPayloadNode
import msw.extras.kxsmine.tree.node.payload.BytePayloadNode
import msw.extras.kxsmine.tree.node.payload.CompoundPayloadNode
import msw.extras.kxsmine.tree.node.payload.DoublePayloadNode
import msw.extras.kxsmine.tree.node.payload.EndPayloadNode
import msw.extras.kxsmine.tree.node.payload.FloatPayloadNode
import msw.extras.kxsmine.tree.node.payload.IntArrayPayloadNode
import msw.extras.kxsmine.tree.node.payload.IntPayloadNode
import msw.extras.kxsmine.tree.node.payload.ListPayloadNode
import msw.extras.kxsmine.tree.node.payload.ListPayloadNode.Companion.listPayloadNode
import msw.extras.kxsmine.tree.node.payload.LongArrayPayloadNode
import msw.extras.kxsmine.tree.node.payload.LongPayloadNode
import msw.extras.kxsmine.tree.node.payload.PayloadNode
import msw.extras.kxsmine.tree.node.payload.ShortPayloadNode
import msw.extras.kxsmine.tree.node.payload.StringPayloadNode
import msw.extras.kxsmine.tree.node.tag.TagNode

@NBTMarker
public class NBTPayloadContext : Collector<PayloadNode<*>>, CompoundCollector {
    private val drain = mutableListOf<PayloadNode<*>>()

    @PublishedApi
    internal val listCollector: ListCollector = ListCollector(drain)

    @PublishedApi
    internal fun <T, P : PayloadNode<T>> addInternal(data: P): P {
        drain.add(data)
        return data
    }

    override fun add(data: PayloadNode<*>): PayloadNode<*> = addInternal(data)
    override fun addAll(data: Iterable<PayloadNode<*>>) {
        drain.addAll(data)
    }

    public fun end(): EndPayloadNode = addInternal(RootCreator.end())
    public fun byte(data: Byte): BytePayloadNode = addInternal(RootCreator.byte(data))
    public fun short(data: Short): ShortPayloadNode = addInternal(RootCreator.short(data))
    public fun int(data: Int): IntPayloadNode = addInternal(RootCreator.int(data))
    public fun long(data: Long): LongPayloadNode = addInternal(RootCreator.long(data))
    public fun float(data: Float): FloatPayloadNode = addInternal(RootCreator.float(data))
    public fun double(data: Double): DoublePayloadNode = addInternal(RootCreator.double(data))
    public fun bytearray(vararg data: Byte): ByteArrayPayloadNode = addInternal(RootCreator.bytearray(*data))
    public fun bytearray(data: List<Byte>): ByteArrayPayloadNode = addInternal(RootCreator.bytearray(data))
    public fun bytearray(builder: Collector<Byte>.() -> Unit): ByteArrayPayloadNode = addInternal(RootCreator.bytearray(builder))
    public fun intarray(vararg data: Int): IntArrayPayloadNode = addInternal(RootCreator.intarray(*data))
    public fun intarray(data: List<Int>): IntArrayPayloadNode = addInternal(RootCreator.intarray(data))
    public fun intarray(builder: Collector<Int>.() -> Unit): IntArrayPayloadNode = addInternal(RootCreator.intarray(builder))
    public fun longarray(vararg data: Long): LongArrayPayloadNode = addInternal(RootCreator.longarray(*data))
    public fun longarray(data: List<Long>): LongArrayPayloadNode = addInternal(RootCreator.longarray(data))
    public fun longarray(builder: Collector<Long>.() -> Unit): LongArrayPayloadNode = addInternal(RootCreator.longarray(builder))
    public fun string(data: String): StringPayloadNode = addInternal(RootCreator.string(data))
    public fun string(vararg data: Char): StringPayloadNode = addInternal(RootCreator.string(*data))
    public fun string(data: StringBuilder): StringPayloadNode = addInternal(RootCreator.string(data))
    public fun string(builder: Collector<Char>.() -> Unit): StringPayloadNode = addInternal(RootCreator.string(builder))
    public inline fun <reified T> list(data: List<T>): ListPayloadNode = listCollector.list(data)
    public inline fun <reified T> list(vararg data: T): ListPayloadNode = listCollector.list(*data)
    public inline fun <reified T> list(builder: Collector<T>.() -> Unit): ListPayloadNode = listCollector.list(builder)
    public fun listlist(builder: ListCollector.() -> Unit): ListPayloadNode = listCollector.listlist(builder)
    public fun compoundlist(builder: CompoundCollector.() -> Unit): ListPayloadNode = listCollector.compoundlist(builder)
    public override fun compound(data: Collection<TagNode<*>>): CompoundPayloadNode = addInternal(RootCreator.compound(data))
    public override fun compound(vararg data: TagNode<*>): CompoundPayloadNode = addInternal(RootCreator.compound(*data))
    public override fun compound(builder: NBTContext.() -> Unit): CompoundPayloadNode = addInternal(RootCreator.compound(builder))

    override fun extract(): List<PayloadNode<*>> = drain

    public object RootCreator {
        public fun end(): EndPayloadNode = EndPayloadNode
        public fun byte(data: Byte): BytePayloadNode = BytePayloadNode(data)
        public fun short(data: Short): ShortPayloadNode = ShortPayloadNode(data)
        public fun int(data: Int): IntPayloadNode = IntPayloadNode(data)
        public fun long(data: Long): LongPayloadNode = LongPayloadNode(data)
        public fun float(data: Float): FloatPayloadNode = FloatPayloadNode(data)
        public fun double(data: Double): DoublePayloadNode = DoublePayloadNode(data)

        public fun bytearray(vararg data: Byte): ByteArrayPayloadNode = ByteArrayPayloadNode(data)
        public fun bytearray(data: List<Byte>): ByteArrayPayloadNode = ByteArrayPayloadNode(data.toByteArray())
        public fun bytearray(builder: Collector<Byte>.() -> Unit): ByteArrayPayloadNode {
            val collector = SimpleCollector<Byte>()
            collector.builder()
            return ByteArrayPayloadNode(collector.extract().toByteArray())
        }

        public fun intarray(vararg data: Int): IntArrayPayloadNode = IntArrayPayloadNode(data)
        public fun intarray(data: List<Int>): IntArrayPayloadNode = IntArrayPayloadNode(data.toIntArray())
        public fun intarray(builder: Collector<Int>.() -> Unit): IntArrayPayloadNode {
            val collector = SimpleCollector<Int>()
            collector.builder()
            return IntArrayPayloadNode(collector.extract().toIntArray())
        }

        public fun longarray(vararg data: Long): LongArrayPayloadNode = LongArrayPayloadNode(data)
        public fun longarray(data: List<Long>): LongArrayPayloadNode = LongArrayPayloadNode(data.toLongArray())
        public fun longarray(builder: Collector<Long>.() -> Unit): LongArrayPayloadNode {
            val collector = SimpleCollector<Long>()
            collector.builder()
            return LongArrayPayloadNode(collector.extract().toLongArray())
        }

        public fun string(data: String): StringPayloadNode = StringPayloadNode(data)
        public fun string(vararg data: Char): StringPayloadNode = StringPayloadNode(data.concatToString())
        public fun string(data: StringBuilder): StringPayloadNode = StringPayloadNode(data.toString())
        public fun string(builder: Collector<Char>.() -> Unit): StringPayloadNode {
            val collector = SimpleCollector<Char>()
            collector.builder()
            return StringPayloadNode(collector.extract().joinToString(""))
        }

        public inline fun <reified T> list(data: List<T>): ListPayloadNode = listPayloadNode(data)
        public inline fun <reified T> list(vararg data: T): ListPayloadNode = listPayloadNode(data.toList())
        public inline fun <reified T> list(builder: Collector<T>.() -> Unit): ListPayloadNode {
            val collector = SimpleCollector<T>()
            collector.builder()
            return listPayloadNode(collector.extract())
        }

        public fun listlist(builder: ListCollector.() -> Unit): ListPayloadNode {
            val collector = ListCollector()
            collector.builder()
            return ListPayloadNode(collector.extract())
        }

        public fun compoundlist(builder: CompoundCollector.() -> Unit): ListPayloadNode {
            val collector = NBTPayloadContext()
            collector.builder()
            return ListPayloadNode(collector.extract().onEach {
                if (it !is CompoundPayloadNode) {
                    throw IllegalArgumentException("Tried to pass non-compound element '$it' to compound list builder")
                }
            })
        }

        public fun compound(data: Collection<TagNode<*>>): CompoundPayloadNode = CompoundPayloadNode(data)
        public fun compound(vararg data: TagNode<*>): CompoundPayloadNode = CompoundPayloadNode(data.toList())
        public fun compound(builder: NBTContext.() -> Unit): CompoundPayloadNode {
            val collector = NBTContext()
            collector.builder()
            return CompoundPayloadNode(collector.extract())
        }
    }
}