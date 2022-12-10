package solutions.day9.logic

import utils.InputReader

object Day9Input {
    val moves = InputReader.readInputLines("day9/input.txt")
        .map { line ->
            line.split(' ').let {
                Move(
                    Direction.fromChar(it[0][0]),
                    it[1].toInt()
                )
            }
        }
}