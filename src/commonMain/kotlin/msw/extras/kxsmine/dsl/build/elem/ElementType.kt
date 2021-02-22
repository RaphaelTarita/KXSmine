package msw.extras.kxsmine.dsl.build.elem

import msw.extras.kxsmine.tree.TagType
import msw.extras.kxsmine.tree.node.payload.PayloadNode

public sealed class ElementType<in I> {
    public abstract val type: TagType
    internal abstract val ctor: (I) -> PayloadNode<in I>

    @PublishedApi
    internal abstract val ctorGeneric: (Any?) -> PayloadNode<*>
}
