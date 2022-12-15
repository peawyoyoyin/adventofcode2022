package solutions.day4

import common.Range
import utils.InputReader
import utils.measureAndLogTime

fun Range.Companion.fromString(string: String) =
    string.split("-")
        .let {
            Range(it[0].trim().toInt(), it[1].trim().toInt())
        }

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