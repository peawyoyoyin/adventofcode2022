package common

data class Range(val start: Int, val end: Int) {
    init {
        if (start > end) {
            throw IllegalArgumentException("range start($start) cannot be more than end($end)")
        }
    }

    val size = end - start + 1

    // start, end inclusive
    infix fun fullyContains(other: Range) = start <= other.start && end >= other.end

    private infix fun overlapsLeftWith(other: Range) = end >= other.start && end <= other.end
    private infix fun overlapsRightWith(other: Range) = start <= other.end && start >= other.start

    infix fun overlapsWith(other: Range) = this overlapsLeftWith other || this overlapsRightWith other
            || this fullyContains other || other fullyContains this

    operator fun contains(num: Int) = num in start..end

    // here for extensions
    companion object
}
