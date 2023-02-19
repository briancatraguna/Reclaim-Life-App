package com.hacknyu.reclaimlife.utils

fun <T> List<T>.from(index: Int): List<T> {
    if (index >= this.size) {
        throw IllegalArgumentException("Index out of bounds")
    }
    val result: MutableList<T> = mutableListOf()
    for (i in index until this.size) {
        result.add(this[i])
    }
    return result
}