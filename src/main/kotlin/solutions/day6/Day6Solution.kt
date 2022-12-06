package solutions.day6

import utils.InputReader
import utils.measureAndLogTime

fun main() = measureAndLogTime {
    val input = InputReader.readInput("day6/input.txt").trim()

    var processed = 0
    val maxQueueSet = LimitedWindowQueueSet<Char>(4)

    for (char in input) {
        processed += 1
        maxQueueSet.push(char)
        if (maxQueueSet.distinctElements == maxQueueSet.maximumSize) {
            break
        }
    }
    println("part 1 ans: $processed")

    processed = 0
    val maxQueueSet2 = LimitedWindowQueueSet<Char>(14)

    for (char in input) {
        processed += 1
        maxQueueSet2.push(char)
        if (maxQueueSet2.distinctElements == maxQueueSet2.maximumSize) {
            break
        }
    }
    println("part 2 ans: $processed")
}