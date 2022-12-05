package solutions.day5

data class Instruction(val move: Int, val from: String, val to: String) {
    companion object {
        fun parse(rawString: String): Instruction =
            // sample format:
            // move 3 from 6 to 2
            rawString.trim().split(" ").let {
                Instruction(move = it[1].toInt(), from = it[3], to = it[5])
            }

    }
}