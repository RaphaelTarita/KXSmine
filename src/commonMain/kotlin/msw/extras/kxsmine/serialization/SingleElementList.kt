package msw.extras.kxsmine.serialization

import msw.extras.kxsmine.exc.NBTSerializationException

internal class SingleElementList<E> : MutableList<E> {
    internal class SingleElementIterator<E>(private val list: SingleElementList<E>) : MutableListIterator<E> {
        private var atElem = false
        override fun hasNext(): Boolean {
            return !atElem && !list.isEmpty()
        }

        override fun next(): E {
            if (!atElem) {
                atElem = true
                return list[0]
            } else {
                throw IndexOutOfBoundsException("index '1' out of bounds for size '${list.size}")
            }
        }

        override fun remove() {
            if (atElem) {
                list.clear()
                atElem = false
            }
        }

        override fun hasPrevious(): Boolean {
            return false
        }

        override fun nextIndex(): Int {
            return if (!atElem) 0 else -1
        }

        override fun previous(): E {
            throw NoSuchElementException("There is no previous element in a SingleElementList")
        }

        override fun previousIndex(): Int {
            return -1
        }

        override fun add(element: E) {
            if (!atElem) {
                list.add(element)
            } else {
                throw NBTSerializationException("SingleElementList was already full")
            }
        }

        override fun set(element: E) {
            if (atElem) {
                list[0] = element
            } else {
                throw NoSuchElementException("Cannot set value at invalid location")
            }
        }
    }

    private var elem: E? = null
    override val size: Int
        get() = if (elem == null) 0 else 1

    override fun contains(element: E): Boolean {
        return element == elem
    }

    override fun containsAll(elements: Collection<E>): Boolean {
        return when (elements.size) {
            0 -> true
            1 -> elements.single() == elem
            else -> false
        }
    }

    override fun get(index: Int): E {
        if (index == 0) {
            return elem ?: throw NoSuchElementException("SingleElementList was empty")
        } else {
            throw IndexOutOfBoundsException("index '$index' out of bounds for size '$size'")
        }
    }

    override fun indexOf(element: E): Int {
        return if (element == elem) 0 else -1
    }

    override fun isEmpty(): Boolean {
        return elem == 0
    }

    override fun iterator(): MutableIterator<E> {
        return SingleElementIterator(this)
    }

    override fun lastIndexOf(element: E): Int {
        return indexOf(element)
    }

    override fun add(element: E): Boolean {
        if (elem == null) {
            elem = element
            return true
        } else {
            throw NBTSerializationException("SingleElementList was already full")
        }
    }

    override fun add(index: Int, element: E) {
        when {
            elem != null -> throw NBTSerializationException("SingleElementList was already full")
            index != 0 -> throw IndexOutOfBoundsException("index '$index' out of bounds for size '$size'")
            else -> elem = element
        }
    }

    override fun addAll(index: Int, elements: Collection<E>): Boolean {
        if (index == 0) {
            return addAll(elements)
        } else {
            throw IndexOutOfBoundsException("index '$index' out of bounds for size '$size'")
        }
    }

    override fun addAll(elements: Collection<E>): Boolean {
        return when (elements.size) {
            0 -> true
            1 -> if (elem == null) {
                elem = elements.single()
                true
            } else {
                throw NBTSerializationException("SingleElementList was already full")
            }
            else -> throw NBTSerializationException("input collection too big for SingleElementList (size ${elements.size})")
        }
    }

    override fun clear() {
        elem = null
    }

    override fun listIterator(): MutableListIterator<E> {
        return SingleElementIterator(this)
    }

    override fun listIterator(index: Int): MutableListIterator<E> {
        return if (index == 0) {
            SingleElementIterator(this).also { it.next() }
        } else {
            throw IndexOutOfBoundsException("index '$index' out of bounds for size '$size'")
        }
    }

    override fun remove(element: E): Boolean {
        return if (element == elem) {
            elem = null
            true
        } else {
            false
        }
    }

    override fun removeAll(elements: Collection<E>): Boolean {
        return when (elem) {
            null -> {
                false
            }
            in elements -> {
                elem = null
                true
            }
            else -> false
        }
    }

    override fun removeAt(index: Int): E {
        if (index == 0) {
            return elem.also { elem = null } ?: throw IndexOutOfBoundsException("index '$index' out of bounds for size '$size'")
        } else {
            throw IndexOutOfBoundsException("index '$index' out of bounds for size '$size'")
        }
    }

    override fun retainAll(elements: Collection<E>): Boolean {
        return when (elem) {
            null -> false
            in elements -> false
            else -> {
                elem = null
                true
            }
        }
    }

    override fun set(index: Int, element: E): E {
        if (index != 0) {
            throw IndexOutOfBoundsException("index '$index' out of bounds for size '$size'")
        } else {
            return elem?.also { elem = null } ?: throw IndexOutOfBoundsException("index '$index' out of bounds for size '$size'")
        }
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<E> {
        if (fromIndex != 0) {
            throw IndexOutOfBoundsException("indices '$fromIndex, $toIndex' out of bounds for size '$size'")
        }
        return when (toIndex) {
            0 -> mutableListOf()
            1 -> this
            else -> throw IndexOutOfBoundsException("indices '$fromIndex, $toIndex' out of bounds for size '$size'")
        }
    }

    internal fun element(): E {
        return elem ?: throw IllegalStateException("SingleElementList was empty")
    }

    internal fun elementOrNull(): E? {
        return elem
    }
}