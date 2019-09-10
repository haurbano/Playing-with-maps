package com.example.googlemapstest.mvp

import com.example.domain.datasources.RouteDataSource
import com.google.android.gms.maps.model.LatLng

class MapModel(
    private val routeDataSource: RouteDataSource
): MapActivityContract.Model {
    override fun getStartPoint(): LatLng {
        return LatLng(routeDataSource.getStartPoint().first, routeDataSource.getStartPoint().second)
    }

    override fun getEndPoint(): LatLng {
        return LatLng(routeDataSource.getEndPoint().first, routeDataSource.getEndPoint().second)
    }
}