package com.example.googlemapstest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.googlemapstest.mvp.MapActivityContract

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.android.ext.android.inject

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, MapActivityContract.View {

    val presenter: MapActivityContract.Presenter by inject()

    private lateinit var mapView: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        presenter.attachView(this)
    }

    override fun showMarkers(startPoint: LatLng, endPoint: LatLng) {
        val startMarker = MarkerOptions().position(startPoint).title(getString(R.string.start_point))
        val endMarker = MarkerOptions().position(endPoint).title(getString(R.string.end_point))

        mapView.addMarker(startMarker)
        mapView.addMarker(endMarker)

        val cameraPosition = CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(startPoint, MapConfig.MAP_ZOOM))
        mapView.moveCamera(cameraPosition)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mapView = googleMap
        presenter.showMarkers()
    }
}
