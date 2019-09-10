package com.example.googlemapstest.mvp

import android.util.Log
import io.reactivex.disposables.CompositeDisposable

class MapPresenter(
    private val model: MapActivityContract.Model
): MapActivityContract.Presenter {

    private val disposables = CompositeDisposable()

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
        val disposable = model.getRoute().subscribe({ points ->
            view.showRoute(points)
        }, { error ->
            Log.e("Error Getting points", error.localizedMessage)
        })

        disposables.add(disposable)
    }
}