package utils

// simple wrapper for stateful iterator, useful for suspendable executions
abstract class StatefulIterator<T>: Iterator<T> {
    internal abstract val underlyingIterator: Iterator<T>

    override fun hasNext(): Boolean = underlyingIterator.hasNext()
    override fun next(): T = underlyingIterator.next()
}