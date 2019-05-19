package com.urbanist.music.feature.create_event

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.urbanist.music.R
import com.urbanist.music.core.presentation.BaseActivity
import com.urbanist.music.databinding.ActivityCreateEventBinding
import javax.inject.Inject

class CreateEventActivity : BaseActivity<ActivityCreateEventBinding>() {
    override val layoutId = R.layout.activity_create_event
    @Inject
    lateinit var viewModel: CreateEventViewModel
    private var currentLocation = Location(LocationManager.GPS_PROVIDER)
    private var isLocationEnabled = true


    override fun initBinding() {
        requireBinding().viewModel = viewModel
    }

    override fun initViewModel(state: Bundle?) {
        viewModel.onBind()
        viewModel.setEventListener(eventsListener)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLocationListener()
    }

    @SuppressLint("MissingPermission")
    private fun initLocationListener() {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            0,
            0f,
            locationListener
        )
    }

    private val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location?) {
            currentLocation = location ?: return
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

        }

        override fun onProviderEnabled(provider: String?) {
            isLocationEnabled = true
        }

        override fun onProviderDisabled(provider: String?) {
            isLocationEnabled = false
        }
    }

    private val eventsListener: CreateEventViewModel.EventsListener =
        object : CreateEventViewModel.EventsListener {
            override fun showMessage(message: String) {
                Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
                    .show()
            }

            override fun routeToMain() {
                showMessage("Событие успешно создано")
                finish()
            }

            override fun getLocation(): Location? {
                if (isLocationEnabled.not()) {
                    showMessage(getString(R.string.report_error_location))
                    return null
                }
                return currentLocation
            }
        }
}