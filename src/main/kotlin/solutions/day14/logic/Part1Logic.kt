package solutions.day14.logic

import utils.IteratorUtils.plus

class Part1Logic(rockLines: List<List<Coordinate>>) : Day14Logic(rockLines) {
    var newSandPosition: Coordinate? = null
    override val underlyingIterator = initRocks + iterator {
        allSand@while (true) {
            newSandPosition = sandFallStart.copy()

            eachSand@while (true) {
                val feasiblePosition = newSandPosition
                    .let { listOf(it!!.fallDown(), it.fallLeft(), it.fallRight()) }
                    .firstOrNull { it !in rocks && it !in sands }

                if (feasiblePosition != null) {
                    newSandPosition = feasiblePosition
                    yield(Unit)
                } else {
                    sands.add(newSandPosition!!)
                    yield(Unit)
                    break@eachSand
                }

                // abyss
                if (newSandPosition!!.second >= maxY) {
                    break@allSand
                }
            }
        }
    }
}
