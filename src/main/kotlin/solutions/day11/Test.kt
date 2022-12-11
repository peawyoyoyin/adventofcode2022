package solutions.day11

import java.math.BigInteger

class Test(spec: String) {
    companion object {
        private val testPattern = "divisible by (\\d+)".toRegex()
    }

    val divider: BigInteger by lazy {
        testPattern.find(spec)?.run {
            groups[1]?.value?.toBigInteger()
        } ?: throw IllegalArgumentException("invalid test pattern $spec")
    }

    fun evaluate(item: Item): Boolean = item.worryLevel % divider == BigInteger.ZERO
}