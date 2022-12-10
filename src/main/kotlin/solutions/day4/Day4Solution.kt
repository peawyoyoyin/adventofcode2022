package solutions.day4

import utils.InputReader
import utils.measureAndLogTime

fun String.toRangePair() = split(',')
    .map(Range::fromString)
    .let{ Pair(it[0], it[1]) }

fun main() = measureAndLogTime {
    val rangePairs = InputReader.readInputLines("day4/input.txt")
        .map { it.toRangePair() }

    // part 1
    rangePairs
        .count { (first, second) ->
            first fullyContains second || second fullyContains first
        }.also {
            println("part 1 ans: $it")
        }

    // part 2
    rangePairs
        .count {
            it.first overlapsWith it.second
        }.also {
            println("part 2 ans: $it")
        }
}