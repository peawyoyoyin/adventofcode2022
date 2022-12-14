package utils

object IteratorUtils {
    operator fun <T> Iterator<T>.plus(other: Iterator<T>) = iterator {
        while (hasNext()) yield(next())
        while (other.hasNext()) yield(other.next())
    }
}
