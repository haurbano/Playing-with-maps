package com.example.domain.datasources

import com.example.domain.models.RouteInfo

interface RouteDataSource {
    fun getStartPoint(): Pair<Double, Double>
    fun getEndPoint(): Pair<Double, Double>

    fun getRoute(): RouteInfo
}