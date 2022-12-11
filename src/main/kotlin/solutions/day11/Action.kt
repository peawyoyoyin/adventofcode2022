package solutions.day11

class Action(spec: String) {
    companion object {
        private val testPattern = "throw to monkey (\\d+)".toRegex()
    }

    val targetMonkeyIndex: Int by lazy {
        testPattern.find(spec)?.run {
            groups[1]?.value?.toInt()
        } ?: throw IllegalArgumentException("invalid action spec $spec")
    }
}