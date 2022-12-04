package solutions.day4

import utils.InputReader
import utils.measureAndLogTime

// start, end inclusive
data class Range(val start: Int, val end: Int) {
    infix fun fullyContains(other: Range) = start <= other.start && end >= other.end

    private infix fun overlapsLeftWith(other: Range) = end >= other.start && end <= other.end
    private infix fun overlapsRightWith(other: Range) = start <= other.end && start >= other.start

    infix fun overlapsWith(other: Range) = this overlapsLeftWith other || this overlapsRightWith other
            || this fullyContains other || other fullyContains this

    companion object {
        fun fromString(string: String) =
            string.split("-")
                .let {
                    Range(it[0].trim().toInt(), it[1].trim().toInt())
                }
    }
}

fun String.toRangePair() = split(',')
    .map(Range::fromString)
    .let{ Pair(it[0], it[1]) }

fun main() = measureAndLogTime {
    val rangePairs = InputReader.readInput("day4/input.txt")
        .trim()
        .split(System.lineSeparator())
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