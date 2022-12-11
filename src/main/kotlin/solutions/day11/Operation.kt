package solutions.day11

import java.math.BigInteger

class Operation(spec: String) {
    companion object {
        private const val tokenPattern = "old|\\d+"
        private val operationPattern = "new = ($tokenPattern) ([+*]) ($tokenPattern)".toRegex()
    }

    private val operand1: String
    private val operand2: String
    private val operator: Char
    init {
        val matches = operationPattern.find(spec)
            ?: throw IllegalArgumentException("invalid operation format $spec")
        operand1 = matches.groups[1]?.value ?: throw IllegalArgumentException("not found operand 1 in $spec")
        operator = matches.groups[2]?.value?.get(0) ?: throw IllegalArgumentException("not found operator in $spec")
        operand2 = matches.groups[3]?.value ?: throw IllegalArgumentException("not found operand 2 in $spec")
    }

    fun evaluate(item: Item, modulo: BigInteger? = null): Item {
        val resolvedOp1 = if (operand1 == "old") item.worryLevel else operand1.toBigInteger()
        val resolvedOp2 = if (operand2 == "old") item.worryLevel else operand2.toBigInteger()

        val newWorryLevelRaw = when (operator) {
            '+' -> resolvedOp1 + resolvedOp2
            '*' -> resolvedOp1 * resolvedOp2
            else -> { throw IllegalArgumentException("unknown operator $operator")}
        }

        val newWorryLevel = if (modulo == null) {
            newWorryLevelRaw
        } else {
            newWorryLevelRaw % modulo
        }
        return Item(newWorryLevel)
    }
}