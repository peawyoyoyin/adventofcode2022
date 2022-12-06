package solutions.day6

open class QueueSet<T> {
    private val queue = mutableListOf<T>()
    private val counts = mutableMapOf<T, Int>()

    val size get() = queue.size
    val distinctElements get() = counts.keys.size

    open fun push(element: T) {
        queue.add(element)

        if (element !in counts) {
            counts[element] = 1
        } else {
            counts[element] = counts[element]!! + 1
        }
    }

    fun pop(): T {
        val element = queue.removeFirst()

        counts[element] = counts[element]!! - 1
        if (counts[element] == 0) {
            counts.remove(element)
        }

        return element
    }
}