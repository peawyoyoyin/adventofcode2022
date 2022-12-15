package solutions.day9.logic

import common.StatefulIterator

class Part1Logic(private val moves: List<Move>): StatefulIterator<Unit>() {
    var headPosition = Position.origin
    var tailPosition = Position.origin

    override val underlyingIterator: Iterator<Unit> = iterator {
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