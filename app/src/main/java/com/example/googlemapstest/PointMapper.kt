package com.example.googlemapstest

import com.google.android.gms.maps.model.LatLng

class PointMapper {
    operator fun invoke(points: List<Pair<Double, Double>>): List<LatLng> {
        return points.map { LatLng(it.first, it.second) }
    }
}