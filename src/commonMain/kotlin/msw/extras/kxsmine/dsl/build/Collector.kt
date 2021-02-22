package msw.extras.kxsmine.dsl.build

@NBTMarker
public interface Collector<T> {
    public fun extract(): List<T>
    public fun add(data: T): T
    public fun addAll(data: Iterable<T>)

    public operator fun T.unaryPlus(): T = add(this)
    public operator fun Iterable<T>.unaryPlus(): Unit = addAll(this)
}