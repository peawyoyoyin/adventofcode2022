package solutions.day5

import utils.InputReader
import utils.measureAndLogTime

fun main() = measureAndLogTime {
    val lineSeperator = System.lineSeparator()
    val rawInput = InputReader.readInput("day5/input.txt").trim()

    // split into initial stack & instructions
    val (rawStacks, rawInstructions) = rawInput.split("$lineSeperator$lineSeperator")
        .let { Pair(it[0], it[1]) }

    val stacks = parseRawStacks(rawStacks)
    val parsedInstructions = rawInstructions.split(lineSeperator).map(Instruction::parse)

    // part 1
    with(stacks.toMutableStacks()) {
        for (instruction in parsedInstructions) {
            val (move, from, to) = instruction

            repeat(move) {
                this[to]!!.add(this[from]!!.last())
                this[from]!!.removeLast()
            }
        }

        topOfStacks()
            .joinToString("")
            .also { println("part 1 ans: $it") }
    }

    // part 2
    with(stacks.toMutableStacks()) {
        for (instruction in parsedInstructions) {
            val (move, from, to) = instruction

            this[to]!!.addAll(this[from]!!.takeLast(move))
            repeat(move) {
                this[from]!!.removeLast()
            }
        }

        topOfStacks()
            .joinToString("")
            .also { println("part 1 ans: $it") }
    }
}