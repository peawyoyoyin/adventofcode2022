package solutions.day14.logic

import solutions.day14.Coordinate
import solutions.day14.moveTowards
import utils.StatefulIterator

abstract class Day14Logic(rockLines: List<List<Coordinate>>) : StatefulIterator<Unit>() {
    companion object {
        val sandFallStart = Pair(500, 0)
    }

    val rocks = mutableSetOf<Coordinate>()
    val sands = mutableSetOf<Coordinate>()

    val maxY = rockLines.maxOf { line -> line.maxOf { it.second } }

    var current: Coordinate? = null

    val initRocks = iterator {
        val sortedRockLines = rockLines.sortedBy {
            line -> line.minOf { it.second }
        }
        for (rockLine in sortedRockLines) {
            val points = rockLine.iterator()
            val firstPoint = points.next()
            current = firstPoint.copy()

            rocks.add(current!!)
            yield(Unit)
            while (points.hasNext()) {
                val nextPoint = points.next()

                while (current != nextPoint) {
                    current = current!! moveTowards nextPoint
                    rocks.add(current!!)
                    yield(Unit)
                }
            }
        }
        current = null
    }
}