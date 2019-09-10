package com.example.data.mappers

import com.example.data.models.RoutePointsResponse
import com.example.domain.models.RouteInfo
import com.google.maps.android.PolyUtil

class RoutePointsResponseMapper {
    operator fun invoke(response: RoutePointsResponse): RouteInfo {
        val polyline = PolyUtil.decode(response.routes.first().overview_polyline.points)
        val points = polyline.map { latLng -> Pair(latLng.latitude, latLng.longitude) }
        return RouteInfo(points = points)
    }
}