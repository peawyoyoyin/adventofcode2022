package solutions.day4

data class Range(val start: Int, val end: Int) {
    // start, end inclusive
    infix fun fullyContains(other: Range) = start <= other.start && end >= other.end

    private infix fun overlapsLeftWith(other: Range) = end >= other.start && end <= other.end
    private infix fun overlapsRightWith(other: Range) = start <= other.end && start >= other.start

    infix fun overlapsWith(other: Range) = this overlapsLeftWith other || this overlapsRightWith other
            || this fullyContains other || other fullyContains this

    companion object {
        fun fromString(string: String) =
            string.split("-")
                .let {
                    Range(it[0].trim().toInt(), it[1].trim().toInt())
                }
    }
}