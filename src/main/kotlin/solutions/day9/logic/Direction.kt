package solutions.day9.logic

enum class Direction {
    Up, Down, Left, Right;

    companion object {
        fun fromChar(char: Char) =
            when(char) {
                'L' -> Left
                'R' -> Right
                'U' -> Up
                'D' -> Down
                else -> { throw IllegalArgumentException("unknown char $char for direction")}
            }
    }
}