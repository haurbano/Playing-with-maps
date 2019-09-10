package com.example.googlemapstest.mvp

class MapPresenter(
    private val model: MapActivityContract.Model
): MapActivityContract.Presenter {

    lateinit var view: MapActivityContract.View

    override fun attachView(view: MapActivityContract.View) {
        this.view = view
    }

    override fun showMarkers() {
        val startPoint = model.getStartPoint()
        val endPoint = model.getEndPoint()
        view.showMarkers(startPoint, endPoint)
    }

    override fun showRoute() {

    }
}