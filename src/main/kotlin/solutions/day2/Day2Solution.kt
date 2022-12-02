package solutions.day2

import utils.InputReader
import utils.measureAndLogTime

fun main() = measureAndLogTime {
    InputReader.readInput("day2/input.txt")
        .trim()
        .split(System.lineSeparator())
        .let { lines ->
            // part 1
            lines
                .sumOf { Round.parseByPart1Logic(it).calculatePlayerScore() }
                .run { println("part 1 ans: $this") }

            // part 2
            lines
                .sumOf { Round.parseByPart2Logic(it).calculatePlayerScore() }
                .run { println("part 2 ans: $this") }
        }
}