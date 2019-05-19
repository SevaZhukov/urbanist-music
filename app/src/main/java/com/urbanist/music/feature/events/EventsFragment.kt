package com.urbanist.music.feature.events

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.urbanist.music.R
import com.urbanist.music.feature.events.recycler.EventsAdapter
import com.urbanist.music.feature.map.domain.Event
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_events.*
import javax.inject.Inject

const val KEY_EVENT = "KEY_EVENT"

class EventsFragment : DaggerFragment(), GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener {
    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onConnected(p0: Bundle?) {
        initLocationListener()
    }

    override fun onConnectionSuspended(p0: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Inject
    lateinit var eventsViewModel: EventsViewModel

    private val adapter: EventsAdapter = EventsAdapter()

    private var currentEventList : ArrayList<Event> = arrayListOf()

    private var currentLocation: Location? = null

    private lateinit var googleApiClient: GoogleApiClient

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        googleApiClient = GoogleApiClient.Builder(context!!)
            .addApi(LocationServices.API)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .build()

        googleApiClient.connect()

        return inflater.inflate(R.layout.fragment_events, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(activity)
        eventRecyclerView.adapter = adapter
        eventRecyclerView.layoutManager = layoutManager
        initViewModel()
    }

    private fun initViewModel() {
        eventsViewModel.onBind()
        eventsViewModel.liveData.observe(this, Observer {
            currentEventList = it as ArrayList<Event>

            Log.i("Adapter", it.toString())
            adapter.updateEvents(getEventsSortedByDistance(it))
        })

        adapter.onEventsClickEvent.observe(this, Observer {
            val navController = Navigation.findNavController(activity!!, R.id.nav_host_fragment)

            val bundle = Bundle()
            bundle.putParcelable(KEY_EVENT, it)
            navController.navigate(R.id.action_events_to_map, bundle)
        })
    }


    private val locationListener = object : com.google.android.gms.location.LocationListener {
        override fun onLocationChanged(location: Location?) {
            currentLocation = location ?: return

            adapter.updateEvents(getEventsSortedByDistance(currentEventList))
        }
    }

    @SuppressLint("MissingPermission")
    private fun initLocationListener() {
        val locationRequest = LocationRequest.create()
        with(locationRequest) {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 180000
        }

        LocationServices.FusedLocationApi
            .requestLocationUpdates(
                googleApiClient,
                locationRequest,
                locationListener
            )
    }


    private fun getEventsSortedByDistance(eventList: List<Event>) =

        eventList.sortedBy {

            val location = Location("")
            location.latitude = currentLocation?.latitude ?: 55.0
            location.longitude = currentLocation?.longitude ?: 83.0

            val location2 = Location("")
            location2.latitude = it.latitude
            location2.longitude = it.longitude

            it.dist = location.distanceTo(location2) / 1000
            location.distanceTo(location2)
        }


}