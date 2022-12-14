package solutions.day14.logic

import utils.InputReader

object Day14Input {
    val rockLines = InputReader.readInputLines("day14/input.txt")
        .distinct()
        .map { line ->
            line.trim()
                .split(" -> ")
                .map { chunk ->
                    chunk.split(",")
                        .let { tokens -> tokens[0].toInt() to tokens[1].toInt()}
                }
        }
}