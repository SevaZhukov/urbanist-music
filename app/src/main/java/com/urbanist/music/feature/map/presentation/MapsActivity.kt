package com.urbanist.music.feature.map.presentation

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import androidx.lifecycle.Observer
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.urbanist.music.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MapsActivity : DaggerAppCompatActivity(), OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMarkerClickListener {

    @Inject
    lateinit var mapsViewModel: MapsViewModel

    private var isFirstLaunch: Boolean = true

    private var currentLocation: Location? = null

    private lateinit var googleMap: GoogleMap

    private lateinit var googleApiClient: GoogleApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        googleApiClient = GoogleApiClient.Builder(this)
            .addApi(LocationServices.API)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .build()

        googleApiClient.connect()
        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        initViewModel()
    }

    private fun initViewModel() {
        mapsViewModel.onBind()
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(map: GoogleMap) {
        this.googleMap = map

        googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        googleMap.isMyLocationEnabled = true
        googleMap.setOnMarkerClickListener(this)

        mapsViewModel.liveData
            .observe(this, Observer { list ->
                list.forEach {
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(LatLng(it.latitude, it.longitude))
                            .title(it.name)
                            .snippet(getFormattedGenres(it.genres))
                    )
                }
            })
    }

    private fun getFormattedGenres(genres: List<String>): String =
        genres
            .toString()
            .replace("[", "")
            .replace("]", "")

    override fun onMarkerClick(clickedMarker: Marker?): Boolean {

        googleMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                clickedMarker!!.position,
                15F
            ), 2000, null
        )

        clickedMarker.showInfoWindow()
        return true
    }


    @SuppressLint("MissingPermission")
    private fun initLocationListener() {
        val locationRequest = LocationRequest.create()
        with(locationRequest) {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 18000
        }

        LocationServices.FusedLocationApi
            .requestLocationUpdates(
                googleApiClient,
                locationRequest,
                locationListener
            )
    }

    private val locationListener = object : com.google.android.gms.location.LocationListener {
        override fun onLocationChanged(location: Location?) {
            currentLocation = location ?: return


            if (isFirstLaunch) {
                googleMap.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        LatLng(
                            currentLocation!!.latitude,
                            currentLocation!!.longitude
                        ),
                        15F
                    )
                )

                isFirstLaunch = false
            }

        }
    }

    override fun onConnected(p0: Bundle?) {
        initLocationListener()
    }

    override fun onConnectionSuspended(p0: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
