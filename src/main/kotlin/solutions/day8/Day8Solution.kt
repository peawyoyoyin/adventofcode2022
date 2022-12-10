package solutions.day8

import utils.InputReader
import utils.measureAndLogTime
import kotlin.math.max
import kotlin.math.min

data class Visibility(var fromLeft: Boolean, var fromRight: Boolean, var fromTop: Boolean, var fromBottom: Boolean) {
    fun visibleFromAnySide() = fromLeft || fromRight || fromTop || fromBottom
}

data class ViewDistance(var toLeft: Int, var toRight: Int, var toTop: Int, var toBottom: Int) {
    fun scenicScore() = toLeft * toRight * toTop * toBottom
}

fun main() = measureAndLogTime {
    val grid = InputReader.readInputLines("day8/input.txt")
        .map { row -> row.toList().map { it.digitToInt() } }

    // part 1
    val visibilities = grid.map {
        it.map {
            Visibility(
                fromLeft = false,
                fromRight = false,
                fromTop = false,
                fromBottom = false
            )
        }
    }

    val numRows = grid.size
    val numCols = grid.first().size

    for (row in 0 until numRows) {
        // left
        var maxFromLeft = -1
        for (col in 0 until numCols) {
            visibilities[row][col].fromLeft = grid[row][col] > maxFromLeft
            maxFromLeft = max(maxFromLeft, grid[row][col])
        }

        // right
        var maxFromRight = -1
        for (col in numCols downTo 0) {
            visibilities[row][col].fromRight = grid[row][col] > maxFromRight
            maxFromRight = max(maxFromRight, grid[row][col])
        }
    }

    for (col in 0 until numCols) {
        // top
        var maxFromTop = -1
        for (row in 0 until numRows) {
            visibilities[row][col].fromTop = grid[row][col] > maxFromTop
            maxFromTop = max(maxFromTop, grid[row][col])
        }

        // bottom
        var maxFromBottom = -1
        for (row in numRows downTo 0) {
            visibilities[row][col].fromBottom = grid[row][col] > maxFromBottom
            maxFromBottom = max(maxFromBottom, grid[row][col])
        }
    }

    visibilities.sumOf { row -> row.count { it.visibleFromAnySide() } }
        .also { println("part 1 ans: $it") }

    // part 2
    val viewDistances = grid.map {
        it.map {
            ViewDistance(0, 0, 0, 0)
        }
    }

    for (row in 0 until numRows) {
        // left
        val lastIndexFromLeft = (0..9).map { 0 }.toMutableList()
        for (col in 0 until numCols) {
            val height = grid[row][col]

            var viewed = 0
            for (j in height..9) {
                viewed = max(viewed, lastIndexFromLeft[j])
            }
            viewDistances[row][col].toLeft = col - viewed
            lastIndexFromLeft[height] = col
        }

        // right
        val lastIndexFromRight = (0..9).map { numRows - 1 }.toMutableList()
        for (col in numCols downTo 0) {
            val height = grid[row][col]

            var viewed = numRows - 1
            for (j in height..9) {
                viewed = min(viewed, lastIndexFromRight[j])
            }
            viewDistances[row][col].toRight = viewed - col
            lastIndexFromRight[height] = col
        }
    }

    for (col in 0 until numCols) {
        // top
        val lastIndexFromTop = (0..9).map { 0 }.toMutableList()
        for (row in 0 until numRows) {
            val height = grid[row][col]

            var viewed = 0
            for (j in height..9) {
                viewed = max(viewed, lastIndexFromTop[j])
            }
            viewDistances[row][col].toTop = row - viewed
            lastIndexFromTop[height] = row
        }

        // bottom
        val lastIndexFromBottom = (0..9).map { numCols - 1 }.toMutableList()
        for (row in numRows downTo 0) {
            val height = grid[row][col]

            var viewed = numCols - 1
            for (j in height..9) {
                viewed = min(viewed, lastIndexFromBottom[j])
            }
            viewDistances[row][col].toBottom = viewed - row
            lastIndexFromBottom[height] = row
        }
    }

    viewDistances.maxOf { row -> row.maxOf { it.scenicScore() } }
        .also { println("part 2 ans: $it") }
}