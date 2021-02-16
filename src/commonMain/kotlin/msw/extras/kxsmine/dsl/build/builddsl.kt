package msw.extras.kxsmine.dsl.build

import msw.extras.kxsmine.tree.node.payload.PayloadNode
import msw.extras.kxsmine.tree.node.tag.TagNode

public fun <T> nbt(builder: NBTContext.RootCreator.() -> TagNode<T>): TagNode<T> {
    return NBTContext.RootCreator.builder()
}

public fun <T> nbtpayload(builder: NBTPayloadContext.RootCreator.() -> PayloadNode<T>): PayloadNode<T> {
    return NBTPayloadContext.RootCreator.builder()
}