package solutions.day11

import java.math.BigInteger

class Monkey(
    val id: Int,
    startingItems: List<Item>,
    private val operation: Operation,
    val test: Test,
    private val actionIfTrue: Action,
    private val actionIfFalse: Action
) {
    var items = startingItems.toMutableList()
    var inspectionCount = 0

    companion object {
        private val lineSeparator: String = System.lineSeparator()
        private val monkeyPattern = (
                "Monkey (.*):$lineSeparator" +
                        "  Starting items: (.*)$lineSeparator" +
                        "  Operation: (.*)$lineSeparator" +
                        "  Test: (.*)$lineSeparator" +
                        "    If true: (.*)$lineSeparator" +
                        "    If false: (.*)"
                ).toRegex()

        fun parse(input: String): Monkey {
            val matches = monkeyPattern.find(input.trim())
                ?: throw IllegalArgumentException("invalid pattern $input")

            val groups = matches.groups
            val idRaw = groups[1]?.value?.trim()
                ?: throw IllegalArgumentException("not found id in input $input")
            val startingItemsRaw = groups[2]?.value?.trim()
                ?: throw IllegalArgumentException("not found starting items in input $input")
            val operationRaw = groups[3]?.value?.trim()
                ?: throw IllegalArgumentException("not found operation in input $input")
            val testRaw = groups[4]?.value?.trim()
                ?: throw IllegalArgumentException("not found test condition in input $input")
            val ifTrueRaw = groups[5]?.value?.trim()
                ?: throw IllegalArgumentException("not found action if true in input $input")
            val ifFalseRaw = groups[6]?.value?.trim()
                ?: throw IllegalArgumentException("not found action if false in input $input")

            val id = idRaw.toInt()
            val startingItems = startingItemsRaw.split(", ").map { Item(it.toBigInteger()) }
            val operation = Operation(operationRaw)
            val test = Test(testRaw)
            val actionIfTrue = Action(ifTrueRaw)
            val actionIfFalse = Action(ifFalseRaw)

            return Monkey(id, startingItems, operation, test, actionIfTrue, actionIfFalse)
        }
    }

    fun turn(relief: Boolean = true, modulo: BigInteger? = null) = iterator {
        while (items.isNotEmpty()) {
            val item = items.removeFirst()
            inspectionCount += 1
            val afterInspection = operation.evaluate(item, modulo).let { if (relief) it.relief() else it }

            val testResult = test.evaluate(afterInspection)

            val targetMonkeyIndex = if (testResult) {
                actionIfTrue.targetMonkeyIndex
            } else {
                actionIfFalse.targetMonkeyIndex
            }

            yield(afterInspection to targetMonkeyIndex)
        }
    }
}