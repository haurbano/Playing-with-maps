package com.example.data

fun Pair<Double, Double>.toLatLngStr() : String {
    return "${this.first},${this.second}"
}