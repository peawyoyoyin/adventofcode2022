package solutions.day10

class CPU(instructions: List<Instruction>) {
    var cycle = 0
    var x = 1
    private val instructionsQueue = instructions.toMutableList()

    // for visualization only
    var currentInstruction: Instruction = Noop
    var isExecutingAddX: Boolean = false

    val sequence = iterator {
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