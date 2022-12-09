package solutions.day9.logic

import kotlin.math.abs

data class Position(val x: Int, val y: Int) {
    companion object {
        val origin = Position(0, 0)
    }

    infix fun moveIn(direction: Direction) =
        when (direction) {
            Direction.Up -> copy(y = y+1)
            Direction.Down -> copy(y = y-1)
            Direction.Left -> copy(x = x-1)
            Direction.Right -> copy(x = x+1)
        }

    infix fun touching(that: Position) =
        (abs(x - that.x) <= 1 && abs(y - that.y) <= 1)

    infix fun moveTowards(that: Position) =
        if (this touching that) {
            copy()
        } else {
            val newX = if (that.x > x) x+1 else if (that.x < x) x-1 else x
            val newY = if (that.y > y) y+1 else if (that.y < y) y-1 else y

            copy(x = newX, y = newY)
        }

    operator fun plus(offset: Position) = copy(x = x + offset.x, y = y + offset.y)
    operator fun times(scale: Int) = copy(x = x*scale, y = y*scale)
}