package solutions.day10

import utils.InputReader

object Day10Input {
    val instructions = InputReader.readInput("day10/input.txt")
        .trim()
        .split(System.lineSeparator())
        .map(Instruction::parse)
}