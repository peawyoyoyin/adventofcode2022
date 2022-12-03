package solutions.day3

import utils.InputReader
import utils.map
import java.lang.IllegalArgumentException

fun String.bisect(): Pair<String, String> {
    val half = length / 2
    return Pair(substring(0, half), substring(half))
}

fun Char.priority(): Int {
    if ('a'.code <= code && code <= 'z'.code) {
        return (code - 'a'.code) + 1
    } else if ('A'.code <= code && code <= 'Z'.code) {
        return (code - 'A'.code) + 27
    }
    throw IllegalArgumentException("cannot find priority for character $this")
}

fun main() {
    InputReader.readInput("day3/input.txt")
        .trim()
        .split(System.lineSeparator())
        .let { lines ->
            // part 1
            lines.sumOf { line ->
                val (firstHalf, secondHalf) =  line.bisect()
                    .map(String::toSet)

                (firstHalf intersect secondHalf).sumOf { it.priority() }
            }.also {
                println("part 1 ans: $it")
            }

            // part 2
            val groups = lines.chunked(3)

            groups.sumOf {
                Triple(it[0], it[1], it[2])
                    .map(String::toSet)
                    .run {
                        (first intersect second intersect third)
                            .sumOf(Char::priority)
                    }
            }.also {
                println("part 2 ans: $it")
            }
        }
}