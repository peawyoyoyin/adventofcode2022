package solutions.day2

import java.lang.IllegalArgumentException

enum class Move(val moveScore: Int) {
    Rock(1), Paper(2), Scissors(3);

    companion object {
        // key wins value
        private val winningMoveMap = mapOf(
            Pair(Rock, Paper),
            Pair(Paper, Scissors),
            Pair(Scissors, Rock)
        )

        private val losingMoveMap by lazy {
            winningMoveMap.entries.associate { (key, value) -> Pair(value, key) }
        }

        fun parse(char: String) = when (char) {
            "A" -> Rock
            "B" -> Paper
            "C" -> Scissors
            else -> { throw IllegalArgumentException("unknown move $char") }
        }

        fun parseByPart1Logic(char: String) = when (char) {
            "X" -> Rock
            "Y" -> Paper
            "Z" -> Scissors
            else -> { throw IllegalArgumentException("unknown part 1 move $char") }
        }
    }

    infix fun against(otherMove: Move) =
        if (otherMove == this) {
            Outcome.Draw
        } else {
            if (this == winningMoveMap[otherMove]) Outcome.Win else Outcome.Lose
        }

    fun getMoveForOutcome(outcome: Outcome) =
        when (outcome) {
            Outcome.Draw -> this
            Outcome.Win -> winningMoveMap[this]!!
            Outcome.Lose -> losingMoveMap[this]!!
        }
}