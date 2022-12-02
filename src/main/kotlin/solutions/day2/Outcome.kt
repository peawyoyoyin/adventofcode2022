package solutions.day2

import java.lang.IllegalArgumentException

enum class Outcome(val score: Int) {
    Win(6), Lose(0), Draw(3);

    companion object {
        fun parse(char: String) = when(char) {
            "X" -> Lose
            "Y" -> Draw
            "Z" -> Win
            else -> { throw IllegalArgumentException("unknown outcome $char") }
        }
    }
}