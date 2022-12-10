package solutions.day10

sealed interface Instruction {
    companion object {
        fun parse(line: String): Instruction {
            val tokens = line.trim().split(' ')

            return when (val instruction = tokens[0]) {
                "noop" -> Noop
                "addx" -> AddX(param = tokens[1].toInt())
                else -> throw IllegalArgumentException("unknown instruction $instruction")
            }
        }
    }
}

object Noop : Instruction
class AddX(val param: Int): Instruction