package solutions.day9

import solutions.day9.logic.*
import utils.measureAndLogTime

fun main() = measureAndLogTime {
    val part1Logic = Part1Logic(Day9Input.moves)

    val visitedPositions = mutableSetOf<Position>()
    while(part1Logic.sequence.hasNext()) {
        part1Logic.sequence.next()

        visitedPositions.add(part1Logic.tailPosition)
    }

    println("part 1 ans: ${visitedPositions.size}")

    visitedPositions.clear()
    val part2Logic = Part2Logic(Day9Input.moves)

    while (part2Logic.sequence.hasNext()) {
        part2Logic.sequence.next()

        visitedPositions.add(part2Logic.knots[9])
    }

    println("part 2 ans: ${visitedPositions.size}")
}
