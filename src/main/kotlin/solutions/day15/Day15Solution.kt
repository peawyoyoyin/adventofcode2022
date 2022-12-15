package solutions.day15

import common.Range
import utils.InputReader
import utils.measureAndLogTime
import kotlin.IllegalArgumentException
import kotlin.math.abs

typealias Coordinate = Pair<Int, Int>

val readingPattern = "^Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)$".toRegex()
data class Reading(val sensorPosition: Coordinate, val closestBeaconPosition: Coordinate) {
    companion object {
        fun parse(rawReading: String): Reading =
            readingPattern.find(rawReading.trim())?.run {
                val sensorX = groups[1]?.value?.toInt()
                    ?: throw IllegalArgumentException("invalid pattern for sensor X in $rawReading")
                val sensorY = groups[2]?.value?.toInt()
                    ?: throw IllegalArgumentException("invalid pattern for sensor Y in $rawReading")
                val closestBeaconX = groups[3]?.value?.toInt()
                    ?: throw IllegalArgumentException("invalid pattern for beacon X in $rawReading")
                val closestBeaconY = groups[4]?.value?.toInt()
                    ?: throw IllegalArgumentException("invalid pattern for beacon Y in $rawReading")

                Reading(sensorPosition = Pair(sensorX, sensorY), closestBeaconPosition = Pair(closestBeaconX, closestBeaconY))
            } ?: throw IllegalArgumentException("invalid reading pattern $rawReading")
    }
}

fun manhattanDistanceBetween(point1: Coordinate, point2: Coordinate)
    = abs(point1.first - point2.first) + abs(point1.second - point2.second)


fun findImpossibleXRanges(readings: List<Reading>, targetY: Int): List<Range> {
    // for each sensor:
    //  given sensor S = (sX, sY)
    //  closest beacon B = (bX, bY)
    //  target T = (tX, tY)
    //      distance to the closest beacon D = md(S, B)
    //      md(S, T) > D
    //      |sX - tX| + |sY - tY| > D
    //                  |sX - tX| > D - |sY - tY|
    //
    //  if |a| > b then a > b or a < -b
    //
    //      sX - tX > D - |sY - tY| -> 1
    //          or
    //      sx - tX < - (D - |sY - tY|) -> 2
    //
    //      tx < sX - D + |sY - tY| -> 1
    //      tx > sx + D - |sY - tY| -> 2
    //
    //  thus t'X where sX - D + |sY - tY| <= t'X <= sx + D - |sY - tY|
    //      cannot be beacon

    val impossibleXRanges = readings.flatMap {
        val (sensorPosition, beaconPosition) = it
        val (sensorX, sensorY) = sensorPosition

        val closestBeaconDistance = manhattanDistanceBetween(sensorPosition, beaconPosition)

        if (abs(sensorY - targetY) > closestBeaconDistance) {
            emptyList()
        } else {
            val minImpossibleX = sensorX - closestBeaconDistance + abs(sensorY - targetY)
            val maxImpossibleX = sensorX + closestBeaconDistance - abs(sensorY - targetY)

            listOf(Range(minImpossibleX, maxImpossibleX))
        }
    }.sortedBy { it.start }

    val mergedImpossibleXRanges = mutableListOf<Range>()
    var currentRange: Range? = null

    for (xRange in impossibleXRanges) {
        if (currentRange == null) {
            currentRange = xRange
        } else {
            if (currentRange.end >= xRange.start) {
                if (currentRange.end < xRange.end) {
                    currentRange = currentRange.copy(end = xRange.end)
                }
            } else {
                mergedImpossibleXRanges.add(currentRange)
                currentRange = xRange
            }
        }
    }
    mergedImpossibleXRanges.add(currentRange!!)

    return mergedImpossibleXRanges
}

fun main() = measureAndLogTime {
    val readings = InputReader.readInputLines("day15/input.txt")
        .map(Reading::parse)

    val part1TargetY = 2_000_000

    val impossibleXRanges = findImpossibleXRanges(readings, part1TargetY)
    val totalImpossibleXs = impossibleXRanges.sumOf { it.size }

    val beaconInImpossibleXRanges = readings
        .map { it.closestBeaconPosition }
        .distinct()
        .filter { (beaconX, beaconY) -> beaconY == part1TargetY && impossibleXRanges.any { beaconX in it } }

    (totalImpossibleXs - beaconInImpossibleXRanges.size).also { println("part 1 ans: $it") }

    val maxY = 4_000_000
    for (targetY in 0..maxY) {
        val impossibleXs = findImpossibleXRanges(readings, targetY)
        if (impossibleXs.size > 1) {
            println(targetY)
            println(impossibleXs)
            break
        }
    }

    // TODO automatically compute answer
}