package solutions.day1

import utils.InputReader

fun main() {
    val lineSeparator = System.getProperty("line.separator")
    val inputs = InputReader.readInput("day1/input.txt")
        .trim()
        .split("$lineSeparator$lineSeparator")
        .map { chunk ->
            chunk.split(lineSeparator).map { it.trim().toInt() }
        }

    val inputsSummed = inputs.map(List<Int>::sum)

    val part1Ans = inputsSummed.maxOrNull()
    println("part 1 ans: $part1Ans")

    val part2Ans = inputsSummed
        .sortedDescending()
        .take(3)
        .sum()

    println("part 2 ans: $part2Ans")
}