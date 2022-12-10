package utils

typealias HomoPair<R> = Pair<R, R>
typealias HomoTriple<R> = Triple<R, R, R>

fun <R, S> HomoPair<R>.map(func: (R) -> S): HomoPair<S> = Pair(func(first), func(second))
fun <R, S> HomoTriple<R>.map(func: (R) -> S): HomoTriple<S> = Triple(func(first), func(second), func(third))