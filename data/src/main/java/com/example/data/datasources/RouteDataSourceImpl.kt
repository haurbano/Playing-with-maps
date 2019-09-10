package com.example.data.datasources

import com.example.data.mappers.RoutePointsResponseMapper
import com.example.data.services.RoutePointServiceProvider
import com.example.data.toLatLngStr
import com.example.domain.datasources.RouteDataSource
import com.example.domain.models.RouteInfo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class RouteDataSourceImpl(
    private val routeRoutePointServiceProvider: RoutePointServiceProvider,
    private val mapper: RoutePointsResponseMapper
): RouteDataSource {

    private val startPoint = Pair(4.667426, -74.056624)
    private val endPoint = Pair(4.672655, -74.054071)
    private val API_KEY = "AIzaSyDGpoAJ4TNvMH6L8HhxLPOemPCN54jk-FI"

    override fun getStartPoint(): Pair<Double, Double> = startPoint

    override fun getEndPoint(): Pair<Double, Double> = endPoint

    override fun getRoute(): Single<RouteInfo> {
        return routeRoutePointServiceProvider.get().getRoutePoints(startPoint.toLatLngStr(), endPoint.toLatLngStr(), API_KEY)
            .map { mapper(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}