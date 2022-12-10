package solutions.day10

import utils.InputReader

object Day10Input {
    val instructions = InputReader.readInputLines("day10/input.txt")
        .map(Instruction::parse)
}