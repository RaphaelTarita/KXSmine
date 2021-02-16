package msw.extras.kxsmine.dsl.build

internal class SimpleCollector<T> : Collector<T> {
    private val drain = mutableListOf<T>()
    override fun extract(): List<T> = drain

    override fun add(data: T): T {
        drain.add(data)
        return data
    }

    override fun addAll(data: Iterable<T>) {
        drain.addAll(data)
    }
}