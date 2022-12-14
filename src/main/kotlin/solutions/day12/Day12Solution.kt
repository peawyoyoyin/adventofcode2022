package solutions.day12

import utils.InputReader
import utils.measureAndLogTime
import java.util.LinkedList

// (row, col)
typealias Coordinate = Pair<Int, Int>

fun Coordinate.adjacents(): List<Coordinate> =
    listOf(
        copy(second = second+1),
        copy(second = second-1),
        copy(first = first+1),
        copy(first = first-1)
    )

fun Coordinate.withInBounds(maxRows: Int, maxCols: Int)
    = first in 0 until maxRows && second in 0 until maxCols

fun Coordinate.adjacentsWithInBounds(maxRows: Int, maxCols: Int)
    = adjacents().filter { it.withInBounds(maxRows, maxCols) }

fun Char.correctHeight() = when(this) {
    'S' -> 'a'
    'E' -> 'z'
    else -> this
}

infix fun Char.canStepTo(that: Char) =
    that.correctHeight() - this.correctHeight() <= 1

fun main() = measureAndLogTime {
    val grid = InputReader.readInputLines("day12/input.txt")
        .map { it.toList() }

    val maxRows = grid.size
    val maxCols = grid[0].size

    // find S & E
    var start: Coordinate? = null
    var end: Coordinate? = null
    for ((rowIndex, row) in grid.withIndex()) {
        for ((colIndex, col) in row.withIndex()) {
            if (col == 'S') {
                start = Pair(rowIndex, colIndex)
            } else if (col == 'E') {
                end = Pair(rowIndex, colIndex)
            }
        }
    }
    if (start == null) throw NoSuchElementException("Not found start")
    if (end == null) throw NoSuchElementException("Not found end")

    val q = LinkedList<Pair<Coordinate, Int>>().apply { add(start to 0) }
    val visited = mutableSetOf(start)

    while (q.isNotEmpty()) {
        val (pos, step) = q.removeFirst()
        if (pos == end) {
            println("part 1 ans $step")
            break
        }

        val (currentRow, currentCol) = pos
        pos.adjacentsWithInBounds(maxRows, maxCols)
            .filter { adj ->
                val (row, col) = adj
                grid[currentRow][currentCol] canStepTo grid[row][col] && adj !in visited
            }
            .forEach {
                visited.add(it)
                q.add((it to step+1))
            }
    }

    val leastStepsRequired = (0 until maxRows).map { (0 until maxCols).map { Int.MAX_VALUE }.toMutableList() }
    visited.clear()
    q.clear()

    q.add(end to 0)
    visited.add(end)

    while (q.isNotEmpty()) {
        val (pos, step) = q.removeFirst()
        val (currentRow, currentCol) = pos
        leastStepsRequired[currentRow][currentCol] = step

        pos.adjacentsWithInBounds(maxRows, maxCols)
            .filter { adj ->
                val (row, col) = adj
                grid[row][col] canStepTo grid[currentRow][currentCol] && adj !in visited
            }
            .forEach {
                visited.add(it)
                q.add(it to step+1)
            }
    }

    leastStepsRequired.flatMapIndexed { rowIndex, row ->
        row.filterIndexed { colIndex, _ ->
            grid[rowIndex][colIndex] == 'a'
        }
    }.min().also { println("part 2 ans: $it") }
}