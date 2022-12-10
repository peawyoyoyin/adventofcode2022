package solutions.day7

sealed interface Node {
    val size: Long
}

data class File(val name: String, override val size: Long): Node
data class Directory(val name: String, val contents: MutableMap<String, Node>): Node {
    override val size by lazy { contents.values.sumOf { it.size } }

    companion object {
        fun listAllSubDirectories(directory: Directory): List<Directory> {
            val subDirectories = directory.contents.values
                .filterIsInstance<Directory>()
                .flatMap { listAllSubDirectories(it) }
            return listOf(directory) +  subDirectories
        }
    }
}