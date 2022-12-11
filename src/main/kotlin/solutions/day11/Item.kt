package solutions.day11

import java.math.BigInteger

data class Item(val worryLevel: BigInteger) {
    fun relief() = copy(worryLevel = worryLevel / BigInteger.valueOf(3))
}