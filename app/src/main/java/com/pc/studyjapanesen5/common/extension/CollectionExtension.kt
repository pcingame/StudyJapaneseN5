package com.pc.studyjapanesen5.common.extension

fun <T> Iterable<T>.partitions(vararg predicates: (T) -> Boolean): List<List<T>> {
    val results = MutableList(predicates.size) { mutableListOf<T>() }
    val others = mutableListOf<T>()

    forEach { item ->
        for (index in predicates.indices) {
            if (predicates[index](item)) {
                results[index].add(item)
                return@forEach
            }
        }
        others.add(item)
    }

    results.add(others)
    return results
}
