package solutions.day7

import utils.InputReader
import utils.measureAndLogTime
import java.util.LinkedList

interface Node {
    val size: Long
}

data class File(val name: String, override val size: Long): Node
data class Directory(val name: String, val contents: MutableMap<String, Node>): Node {
    override val size by lazy { contents.values.sumOf { it.size } }
}

val commandRegex = Regex("\\$ .*")

val cdRegex = Regex("\\$ cd (.*)")
val lsRegex = Regex("\\$ ls")

val fileRegex = Regex("(\\d+) (.*)")
val dirRegex = Regex("dir (.*)")

fun buildDirectories(lines: List<String>): Directory {
    val rootDirectory = Directory("/", mutableMapOf())

    val directoryStack = mutableListOf(rootDirectory)
    var cwd = rootDirectory

    val queue = LinkedList(lines)

    while (queue.isNotEmpty()) {
        var line = queue.removeFirst()
        assert(commandRegex.matches(line))

        val cdMatches = cdRegex.find(line)
        if (cdMatches != null) {
            cwd = when (val dirName = cdMatches.groups[1]!!.value) {
                "/" -> {
                    directoryStack.clear()
                    directoryStack.add(rootDirectory)
                    rootDirectory
                }
                ".." -> {
                    directoryStack.removeLast()
                    directoryStack.last()
                }
                else -> {
                    (cwd.contents[dirName] as Directory).also {
                        directoryStack.add(it)
                    }
                }
            }
            continue
        }

        if (lsRegex.matches(line)) {
            while (queue.isNotEmpty() && !commandRegex.matches(queue.first())) {
                line = queue.removeFirst()
                val dirMatches = dirRegex.find(line)
                if (dirMatches != null) {
                    val dirName = dirMatches.groups[1]!!.value
                    cwd.contents[dirName] = Directory(dirName, mutableMapOf())
                    continue
                }

                val fileMatches = fileRegex.find(line)
                if (fileMatches != null) {
                    val fileSize = fileMatches.groups[1]!!.value.toLong()
                    val fileName = fileMatches.groups[2]!!.value
                    cwd.contents[fileName] = File(fileName, fileSize)
                }
            }
            continue
        }

        throw IllegalArgumentException("unexpected line $line")
    }

    return rootDirectory
}

fun main() = measureAndLogTime {
    val lines = InputReader.readInput("day7/input.txt")
        .trim()
        .split(System.lineSeparator())

    val rootDirectory = buildDirectories(lines)

    val threshold = 100000L
    // part 2
    val totalDiskSpace = 70_000_000L
    val targetFreeDiskSpace = 30_000_000L
    val currentUsedDiskSpace = rootDirectory.size

    val currentFreeSpace = totalDiskSpace - currentUsedDiskSpace
    val spaceNeedToFree = targetFreeDiskSpace - currentFreeSpace

    // DFS
    val allDirectories = mutableListOf<Directory>()
    val stack = mutableListOf<Directory>()

    stack.add(rootDirectory)
    while (stack.isNotEmpty()) {
        val node = stack.removeLast()
        allDirectories.add(node)
        stack.addAll(node.contents.values.filterIsInstance<Directory>())
    }

    // part 1
    allDirectories
        .filter { it.size <= threshold }
        .sumOf { it.size }
        .also { println("part 1 ans: $it")}

    // part 2
    allDirectories
        .filter { it.size >= spaceNeedToFree }
        .minOf { it.size }
        .also { println("part 2 ans: $it") }
}