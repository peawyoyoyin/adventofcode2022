package utils

import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@OptIn(ExperimentalTime::class)
fun measureAndLogTime(block: () -> Unit) {
    measureTime(block).also {
        println("\n[measure] Time taken: ${it.inWholeMilliseconds} ms")
    }
}