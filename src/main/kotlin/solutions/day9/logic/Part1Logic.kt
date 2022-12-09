package solutions.day9.logic

class Part1Logic(private val moves: List<Move>) {
    var headPosition = Position.origin
    var tailPosition = Position.origin

    val sequence by lazy {
        iterator {
            for (move in moves) {
                val (direction, distance) = move

                repeat(distance) {
                    headPosition = headPosition moveIn direction
                    tailPosition = tailPosition moveTowards headPosition

                    yield(Unit)
                }
            }
        }
    }
}