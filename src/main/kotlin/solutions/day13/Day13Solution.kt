package solutions.day13

import utils.InputReader
import utils.measureAndLogTime

fun main() = measureAndLogTime {
    val lineSeparator = System.lineSeparator()
    val inputs = InputReader.readInput("day13/input.txt")
        .split("$lineSeparator$lineSeparator")
        .map { group ->
            group.split(System.lineSeparator())
                .map { Node.parse(it.trim()) }
                .let { Pair(it[0], it[1])}
        }

    inputs
        .withIndex()
        .filter { (_, pair) -> pair.first < pair.second }
        .sumOf { (index, _) -> index + 1 }
        .also { println("part 1 ans: $it") }

    val dividerPacket1 = Node.parse("[[2]]")
    val dividerPacket2 = Node.parse("[[6]]")

    inputs
        .flatMap { it.toList() }
        .let { it + listOf(dividerPacket1, dividerPacket2) }
        .sortedBy { it }
        .let { (it.indexOf(dividerPacket1) + 1) * (it.indexOf(dividerPacket2) + 1) }
        .also { println("part 2 ans: $it") }
}