package solutions.day5

typealias Stacks = Map<String, List<Char>>
typealias MutableStacks = Map<String, MutableList<Char>>

fun parseRawStacks(rawStacks: String) =
    rawStacks.trim()
        .split(System.lineSeparator())
        .reversed()
        .let { reversedLines ->
            // first line is stack labels
            val stackLabels = reversedLines.first()
                .chunked(4)
                .map(String::trim)
            val rawStackContents = reversedLines.drop(1)
                .map { it.chunked(4).map(String::trim) }

            val stacks = stackLabels.indices.map { mutableListOf<Char>() }

            rawStackContents.forEach { line ->
                line.forEachIndexed { index, s ->
                    if (s.isNotEmpty()) {
                        // example format [Q]
                        stacks[index].add(s.trim()[1])
                    }
                }
            }

            (stackLabels zip stacks.map { it.toList() }).toMap()
        }

fun Stacks.toMutableStacks(): MutableStacks = mapValues {  (_, value) -> value.toMutableList() }
fun MutableStacks.topOfStacks() = toList().sortedBy { it.first }.map { it.second.last() }