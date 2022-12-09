package solutions.day9.logic

class Part2Logic(private val moves: List<Move>) {
    val knots = (0..9).map { Position.origin }.toMutableList()

    val sequence by lazy {
        iterator {
            for (move in moves) {
                val (direction, distance) = move

                repeat(distance) {
                    knots[0] = knots[0] moveIn direction
                    for (i in 1..9) {
                        knots[i] = knots[i] moveTowards knots[i-1]
                    }

                    yield(Unit)
                }
            }
        }
    }
}