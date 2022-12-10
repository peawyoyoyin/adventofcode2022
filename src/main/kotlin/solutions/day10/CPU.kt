package solutions.day10

class CPU(instructions: List<Instruction>) {
    var cycle = 0
    var x = 1
    private val instructionsQueue = instructions.toMutableList()

    val sequence = iterator {
        while (instructionsQueue.isNotEmpty()) {
            when (val instruction = instructionsQueue.removeFirst()) {
                is AddX -> {
                    cycle += 1
                    yield(Unit)
                    cycle += 1
                    yield(Unit)
                    x += instruction.param
                }
                Noop -> {
                    cycle += 1
                    yield(Unit)
                }
            }
        }
    }
}