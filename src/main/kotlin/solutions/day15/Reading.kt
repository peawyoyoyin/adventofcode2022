package solutions.day15

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

                Reading(
                    sensorPosition = Pair(sensorX, sensorY),
                    closestBeaconPosition = Pair(closestBeaconX, closestBeaconY)
                )
            } ?: throw IllegalArgumentException("invalid reading pattern $rawReading")
    }
}