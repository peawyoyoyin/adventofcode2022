package solutions.day14

import utils.InputReader
import utils.measureAndLogTime

typealias Coordinate = Pair<Int, Int>

enum class State {
    Air, Sand, Rock;
}

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

fun main() = measureAndLogTime {
    val rockLines = InputReader.readInputLines("day14/input.txt")
        .map { line ->
            line.trim()
                .split(" -> ")
                .map { chunk ->
                    chunk.split(",")
                        .let { tokens -> tokens[0].toInt() to tokens[1].toInt()}
                }
        }

    val rocks = mutableSetOf<Coordinate>()
    val sands = mutableSetOf<Coordinate>()

    val grid = (0 until 1000).map { (0 until 1000).map { State.Air }.toMutableList() }

    for (rockLine in rockLines) {
        val points = rockLine.iterator()
        val firstPoint = points.next()
        var current = firstPoint.copy()

        val (firstX, firstY) = firstPoint
        grid[firstX][firstY] = State.Rock
        rocks.add(current)
        while (points.hasNext()) {
            val nextPoint = points.next()

            while (current != nextPoint) {
                current = current moveTowards nextPoint
                rocks.add(current)
                val (x, y) = current
                grid[x][y] = State.Rock
            }
        }
    }

    val maxY = rockLines.maxOf { line -> line.maxOf { it.second } }

    var sandUnits = 0
    val sandFallStart = Pair(500, 0)

    allSandPart1@while (true) {
        var newSandPosition = sandFallStart.copy()

        eachSandPart1@while (true) {
            val feasiblePosition = newSandPosition
                .let { listOf(it.fallDown(), it.fallLeft(), it.fallRight()) }
                .firstOrNull { it !in rocks && it !in sands }

            if (feasiblePosition != null) {
                newSandPosition = feasiblePosition
            } else {
                sands.add(newSandPosition)
                sandUnits += 1
                break@eachSandPart1
            }

            // abyss
            if (newSandPosition.second >= maxY) {
                break@allSandPart1
            }
        }
    }

    println("part 1 ans: $sandUnits")

    sandUnits = 0
    sands.clear()
    allSandPart2@while (true) {
        var newSandPosition = sandFallStart.copy()

        eachSandPart2@while (true) {
            val maxPossibleY = maxY + 2

            val feasiblePosition = newSandPosition
                .let { listOf(it.fallDown(), it.fallLeft(), it.fallRight()) }
                .firstOrNull { it !in rocks && it !in sands && it.second < maxPossibleY }

            if (feasiblePosition != null) {
                newSandPosition = feasiblePosition
            } else {
                sands.add(newSandPosition)
                sandUnits += 1
                break@eachSandPart2
            }
        }

        if (rocks.contains(sandFallStart) || sands.contains(sandFallStart)) {
            break@allSandPart2
        }
    }

    println("part 2 ans: $sandUnits")

}