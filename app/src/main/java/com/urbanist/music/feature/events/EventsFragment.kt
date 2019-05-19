package com.urbanist.music.feature.events

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.urbanist.music.R
import com.urbanist.music.feature.events.recycler.EventsAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_events.*
import javax.inject.Inject

class EventsFragment : DaggerFragment() {

    @Inject
    lateinit var eventsViewModel: EventsViewModel

    private val adapter : EventsAdapter = EventsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

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
            Log.i("Adapter", it.toString())
            adapter.updateEvents(it)
        })
    }
}