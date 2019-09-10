package com.example.googlemapstest.mvp

import com.google.android.gms.maps.model.LatLng
import io.reactivex.Single

interface MapActivityContract {
    interface Presenter {
        fun showMarkers()
        fun showRoute()
        fun attachView(view: View)
    }

    interface Model {
        fun getStartPoint(): LatLng
        fun getEndPoint(): LatLng
        fun getRoute(): Single<List<LatLng>>

    }

    interface View {
        fun showMarkers(startPoint: LatLng, endPoint: LatLng)
        fun showRoute(points: List<LatLng>)
    }
}