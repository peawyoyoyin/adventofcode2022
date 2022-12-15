package solutions.day10

import common.StatefulIterator

class CPU(instructions: List<Instruction>): StatefulIterator<Unit>() {
    var cycle = 0
    var x = 1
    private val instructionsQueue = instructions.toMutableList()

    // for visualization only
    var currentInstruction: Instruction = Noop
    var isExecutingAddX: Boolean = false

    override val underlyingIterator: Iterator<Unit> = iterator {
        while (instructionsQueue.isNotEmpty()) {
            currentInstruction = instructionsQueue.removeFirst()
            when (currentInstruction) {
                is AddX -> {
                    isExecutingAddX = true
                    cycle += 1
                    yield(Unit)
                    isExecutingAddX = false
                    cycle += 1
                    yield(Unit)
                    x += (currentInstruction as AddX).param
                }
                Noop -> {
                    cycle += 1
                    yield(Unit)
                }
            }
        }
    }
}