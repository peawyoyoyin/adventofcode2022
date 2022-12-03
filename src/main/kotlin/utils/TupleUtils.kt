package utils

fun <R, S> Pair<R, R>.map(func: (R) -> S) = Pair(func(first), func(second))
fun <R, S> Triple<R, R, R>.map(func: (R) -> S) = Triple(func(first), func(second), func(third))