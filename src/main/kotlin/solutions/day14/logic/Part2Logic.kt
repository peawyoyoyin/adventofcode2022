package solutions.day14.logic

import utils.IteratorUtils.plus

class Part2Logic(rockLines: List<List<Coordinate>>) : Day14Logic(rockLines) {
    var newSandPosition: Coordinate? = null

    override val underlyingIterator = initRocks + iterator {
        allSand@while (true) {
            newSandPosition= sandFallStart.copy()

            eachSand@while (true) {
                val maxPossibleY = maxY + 2

                val feasiblePosition = newSandPosition
                    .let { listOf(it!!.fallDown(), it.fallLeft(), it.fallRight()) }
                    .firstOrNull { it !in rocks && it !in sands && it.second < maxPossibleY }

                if (feasiblePosition != null) {
                    newSandPosition = feasiblePosition
                    yield(Unit)
                } else {
                    sands.add(newSandPosition!!)
                    yield(Unit)
                    break@eachSand
                }
            }

            if (sandFallStart in rocks || sandFallStart in sands) {
                break@allSand
            }
        }

        yield(Unit)
    }
}