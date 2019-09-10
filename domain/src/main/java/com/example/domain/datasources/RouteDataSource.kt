package com.example.domain.datasources

import com.example.domain.models.RouteInfo
import io.reactivex.Single

interface RouteDataSource {
    fun getStartPoint(): Pair<Double, Double>
    fun getEndPoint(): Pair<Double, Double>

    fun getRoute(): Single<RouteInfo>
}