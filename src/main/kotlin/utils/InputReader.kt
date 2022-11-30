package utils

import kotlin.io.path.Path

object InputReader {
    private val inputDirectory = Path(System.getProperty("user.dir")).resolve("./inputs")

    fun readInput(filename: String): String {
        val fullPath = inputDirectory.resolve(filename)

        return fullPath.toFile().readText()
    }
}