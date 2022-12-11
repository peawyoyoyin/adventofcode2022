package solutions.day11

import utils.InputReader
import utils.measureAndLogTime

val lineSeparator: String = System.lineSeparator()
fun readMonkeys() = InputReader.readInput("day11/input.txt")
    .trim()
    .split("$lineSeparator$lineSeparator")
    .map(Monkey::parse)

fun main() = measureAndLogTime {
    val monkeys = readMonkeys()

    for (round in 1..20) {
        for (monkey in monkeys) {
            for ((item, target) in monkey.turn()) {
                monkeys[target].items.add(item)
            }
        }
    }

    monkeys
        .sortedByDescending { it.inspectionCount }
        .take(2)
        .let {  it[0].inspectionCount * it[1].inspectionCount }
        .also { println("part 1 ans: $it") }

    val part2Monkeys = readMonkeys()
    val modulo = part2Monkeys
        .map { it.test.divider }
        .reduce { acc, value -> acc * value }

    for (round in 1..10000) {
        for (monkey in part2Monkeys) {
            for ((item, target) in monkey.turn(relief = false, modulo)) {
                part2Monkeys[target].items.add(item)
            }
        }
    }
    part2Monkeys
        .sortedByDescending { it.inspectionCount }
        .take(2)
        .let { it[0].inspectionCount.toBigInteger() * it[1].inspectionCount.toBigInteger() }
        .also { println("part 2 ans: $it") }
}
