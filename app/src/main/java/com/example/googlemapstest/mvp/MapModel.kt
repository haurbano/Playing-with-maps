package com.example.googlemapstest.mvp

import com.example.domain.datasources.RouteDataSource
import com.example.googlemapstest.PointMapper
import com.google.android.gms.maps.model.LatLng
import io.reactivex.Single

class MapModel(
    private val routeDataSource: RouteDataSource,
    private val pointMapper: PointMapper
): MapActivityContract.Model {
    override fun getStartPoint(): LatLng {
        return LatLng(routeDataSource.getStartPoint().first, routeDataSource.getStartPoint().second)
    }

    override fun getEndPoint(): LatLng {
        return LatLng(routeDataSource.getEndPoint().first, routeDataSource.getEndPoint().second)
    }

    override fun getRoute(): Single<List<LatLng>> {
        return routeDataSource.getRoute().map { pointMapper(it.points) }
    }
}