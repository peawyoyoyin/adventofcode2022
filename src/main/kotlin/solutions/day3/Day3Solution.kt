package solutions.day3

import utils.InputReader
import utils.map
import utils.measureAndLogTime
import java.lang.IllegalArgumentException

fun String.bisect(): Pair<String, String> {
    val half = length / 2
    return Pair(substring(0, half), substring(half))
}

fun Char.priority() = when (category) {
    CharCategory.LOWERCASE_LETTER -> this - 'a' + 1
    CharCategory.UPPERCASE_LETTER -> this - 'A' + 27
    else -> throw IllegalArgumentException("cannot find priority for character $this")
}

fun main() = measureAndLogTime {
    InputReader.readInputLines("day3/input.txt")
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