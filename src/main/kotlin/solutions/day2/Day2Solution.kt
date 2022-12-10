package solutions.day2

import utils.InputReader
import utils.measureAndLogTime

fun main() = measureAndLogTime {
    InputReader.readInputLines("day2/input.txt")
        .let { lines ->
            // part 1
            lines
                .sumOf { Round.parseByPart1Logic(it).calculatePlayerScore() }
                .let { println("part 1 ans: $it") }

            // part 2
            lines
                .sumOf { Round.parseByPart2Logic(it).calculatePlayerScore() }
                .let { println("part 2 ans: $it") }
        }
}