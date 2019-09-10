package com.example.data.datasources

import com.example.domain.datasources.RouteDataSource
import com.example.domain.models.RouteInfo


class RouteDataSourceImpl(): RouteDataSource {

    private val startPoint = Pair(4.667426, -74.056624)
    private val endPoint = Pair(4.672655, -74.054071)

    override fun getStartPoint(): Pair<Double, Double> = startPoint

    override fun getEndPoint(): Pair<Double, Double> = endPoint

    override fun getRoute(): RouteInfo {
        return RouteInfo()
    }

}