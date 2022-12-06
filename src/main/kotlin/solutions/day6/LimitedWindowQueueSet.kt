package solutions.day6

class LimitedWindowQueueSet<T>(val maximumSize: Int): QueueSet<T>() {
    private fun pushAndReceive(element: T): T? {
        super.push(element)
        if (size > maximumSize) {
            return super.pop()
        }
        return null
    }

    override fun push(element: T) {
        pushAndReceive(element)
    }
}