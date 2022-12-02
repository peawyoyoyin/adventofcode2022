package utils

import kotlin.io.path.Path

object InputReader {
    private val inputDirectory = Path(System.getProperty("user.dir")).resolve("./inputs")

    fun readInput(filename: String) =
        inputDirectory
            .resolve(filename)
            .toFile()
            .readText()
}