package com.example.googlemapstest.mvp

import com.google.android.gms.maps.model.LatLng

interface MapActivityContract {
    interface Presenter {
        fun showMarkers()
        fun showRoute()
        fun attachView(view: MapActivityContract.View)
    }

    interface Model {
        fun getStartPoint(): LatLng
        fun getEndPoint(): LatLng
    }

    interface View {
        fun showMarkers(startPoint: LatLng, endPoint: LatLng)
    }
}