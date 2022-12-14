package solutions.day14

import solutions.day14.logic.Day14Input
import solutions.day14.logic.Part1Logic
import solutions.day14.logic.Part2Logic
import utils.measureAndLogTime

fun main() = measureAndLogTime {
    val part1 = Part1Logic(Day14Input.rockLines)
    while (part1.hasNext()) { part1.next() }
    println("part 1 ans: ${part1.sands.size}")

    val part2 = Part2Logic(Day14Input.rockLines)
    while (part2.hasNext()) { part2.next() }
    println("part 2 ans: ${part2.sands.size}")
}