package solutions.day13

sealed class Node: Comparable<Node> {
    companion object {
        fun parse(line: String): Node {
            val stack = mutableListOf<MutableList<Node>>()
            val acc = mutableListOf<Char>()

            for (c in line) {
                when (c) {
                    '[' -> { stack.add(mutableListOf())}
                    ']' -> {
                        if (acc.isNotEmpty()) {
                            stack.last() += acc.joinToString("").toInt().toIntNode()
                            acc.clear()
                        }
                        val top = stack.removeLast()
                        if (stack.isEmpty()) {
                            stack.add(top)
                            break
                        }
                        stack.last().add(ListNode(top))
                    }
                    ',' -> {
                        if (acc.isNotEmpty()) {
                            stack.last() += acc.joinToString("").toInt().toIntNode()
                            acc.clear()
                        }
                    }
                    else -> { acc += c }
                }
            }

            return ListNode(stack.first())
        }
    }

    // TODO this seems like a stub, maybe we can do hashcode by the underlying value?
    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Node) return false

        return when {
            this is IntNode && other is IntNode -> {
                this.value == other.value
            }
            this is ListNode && other is ListNode -> {
                this.value.size == other.value.size &&
                    this.value.zip(other.value).all { (l, r) -> l == r }
            }
            else -> false
        }
    }

    override operator fun compareTo(other: Node): Int {
        when {
            this is IntNode && other is IntNode -> {
                return this.value.compareTo(other.value)
            }
            this is ListNode && other is ListNode -> {
                val thisIterator = this.value.iterator()
                val otherIterator = other.value.iterator()

                while (thisIterator.hasNext() && otherIterator.hasNext()) {
                    val nextOfThis = thisIterator.next()
                    val nextOfOther = otherIterator.next()

                    val compareResult = nextOfThis.compareTo(nextOfOther)
                    if (compareResult != 0) return compareResult
                }

                // this has next = other runs out first
                if (thisIterator.hasNext()) return 1

                // other has next = this runs out first
                if (otherIterator.hasNext()) return -1
                return 0
            }
            this is IntNode && other is ListNode -> {
                return this.toListNode().compareTo(other)
            }
            this is ListNode && other is IntNode -> {
                return this.compareTo(other.toListNode())
            }
        }
        return 0
    }
}

fun Int.toIntNode() = IntNode(this)

data class IntNode(val value: Int): Node() {
    fun toListNode() = ListNode(listOf(IntNode(value)))
    override fun toString() = "$value"
}

data class ListNode(val value: List<Node>): Node() {
    override fun toString() = "[${value.joinToString(",")}]"
}