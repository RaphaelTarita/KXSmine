package msw.extras.kxsmine.dsl.build

import msw.extras.kxsmine.tree.node.payload.PayloadNode
import msw.extras.kxsmine.tree.node.tag.TagNode

public inline fun <T, N : TagNode<T>> nbt(builder: NBTContext.RootCreator.() -> N): N {
    return NBTContext.RootCreator.builder()
}

public inline fun <T, P : PayloadNode<T>> nbtpayload(builder: NBTPayloadContext.RootCreator.() -> P): P {
    return NBTPayloadContext.RootCreator.builder()
}