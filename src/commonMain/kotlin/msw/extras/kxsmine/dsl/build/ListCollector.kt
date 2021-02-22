package msw.extras.kxsmine.dsl.build

import msw.extras.kxsmine.tree.node.payload.ListPayloadNode
import msw.extras.kxsmine.tree.node.payload.PayloadNode

@NBTMarker
public class ListCollector(
    @PublishedApi
    internal val drain: MutableList<PayloadNode<*>> = mutableListOf()
) {
    public inline fun <reified T> list(data: List<T>): ListPayloadNode {
        val node = NBTPayloadContext.RootCreator.list(data)
        drain.add(node)
        return node
    }

    public inline fun <reified T> list(vararg data: T): ListPayloadNode {
        val node = NBTPayloadContext.RootCreator.list(*data)
        drain.add(node)
        return node
    }

    public inline fun <reified T> list(builder: Collector<T>.() -> Unit): ListPayloadNode {
        val node = NBTPayloadContext.RootCreator.list(builder)
        drain.add(node)
        return node
    }

    public fun listlist(builder: ListCollector.() -> Unit): ListPayloadNode {
        val node = NBTPayloadContext.RootCreator.listlist(builder)
        drain.add(node)
        return node
    }

    public fun compoundlist(builder: CompoundCollector.() -> Unit): ListPayloadNode {
        val node = NBTPayloadContext.RootCreator.compoundlist(builder)
        drain.add(node)
        return node
    }

    public fun extract(): List<ListPayloadNode> = drain.map {
        if (it is ListPayloadNode) it
        else throw IllegalArgumentException("Tried to pass non-list element '$it' to list list builder")
    }
}