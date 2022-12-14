package solutions.day14

typealias Coordinate = Pair<Int, Int>

infix fun Coordinate.moveTowards(other: Coordinate) =
    when {
        other.first == this.first -> {
            if (this.second < other.second) {
                copy(second = second + 1)
            } else {
                copy(second = second - 1)
            }
        }
        other.second == this.second -> {
            if (this.first < other.first) {
                copy (first = first + 1)
            } else {
                copy(first = first - 1)
            }
        }

        else -> { throw IllegalArgumentException("moving two dimensions at the same time from $this to $other") }
    }

fun Coordinate.fallDown() = copy(second = second+1)
fun Coordinate.fallLeft() = copy(first = first-1, second = second+1)
fun Coordinate.fallRight() = copy(first = first+1, second = second+1)