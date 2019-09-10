package com.example.googlemapstest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.googlemapstest.mvp.MapActivityContract

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import org.koin.android.ext.android.inject
import androidx.core.app.ActivityCompat
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, MapActivityContract.View {

    val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 10

    private val presenter: MapActivityContract.Presenter by inject()
    private var locationPermissionGranted = false

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
        presenter.showRoute()
    }

    override fun showRoute(points: List<LatLng>) {
        val polylineOptions = PolylineOptions()
        polylineOptions.addAll(points)
        mapView.addPolyline(polylineOptions)
    }

    override fun onResume() {
        super.onResume()
        getLocationPermission()
    }

    private fun getLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this.applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            locationPermissionGranted = true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        locationPermissionGranted = false
        when (requestCode) {
            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {
                if (grantResults.isNotEmpty() && grantResults[0] === PackageManager.PERMISSION_GRANTED) {
                    locationPermissionGranted = true
                    listenLocationChanges()
                }
            }
        }
    }

    private fun listenLocationChanges() {
        if (mapView == null) return
        if(locationPermissionGranted) {
            mapView.isMyLocationEnabled = true
            listenAndUpdateLocation()
        }
    }

    private fun listenAndUpdateLocation() {

    }

    private fun updateLocationonUI() {

    }

}
