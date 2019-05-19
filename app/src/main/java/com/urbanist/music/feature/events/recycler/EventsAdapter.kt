package com.urbanist.music.feature.events.recycler

import android.location.Location
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.google.type.LatLng
import com.urbanist.music.R
import com.urbanist.music.databinding.ItemEventBinding
import com.urbanist.music.feature.map.domain.Event

class EventsAdapter constructor(
    private var currentPosition: LatLng
) : RecyclerView.Adapter<EventsListViewHolder>() {


    var eventList: List<Event> = arrayListOf()

    val onEventsClickEvent = MutableLiveData<Event>()

    fun updateEvents(eventList: List<Event>) {
        sortByDistance()
        this.eventList = eventList
        notifyDataSetChanged()
    }

    fun updateCurrentPosition(latLng: LatLng) {
        currentPosition = latLng
    }

    private fun sortByDistance() {

        eventList.sortedBy {

            val location = Location("")
            location.latitude = currentPosition.latitude
            location.longitude = currentPosition.longitude

            val location2 = Location("")
            location2.latitude = it.latitude
            location2.longitude = it.longitude

            it.dist = location.distanceTo(location2)
            location.distanceTo(location2)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate(inflater, R.layout.item_event, parent, false) as ItemEventBinding
        return EventsListViewHolder(binding)
    }

    override fun getItemCount(): Int = eventList.size

    override fun onBindViewHolder(holder: EventsListViewHolder, position: Int) {

        holder.binding?.event = eventList[position]
        holder.binding?.root?.setOnClickListener { onEventsClickEvent.value = eventList[position] }
        holder.binding?.executePendingBindings()
    }
}

class EventsListViewHolder(binding: ItemEventBinding) : RecyclerView.ViewHolder(binding.root) {

    var binding: ItemEventBinding? = binding
}


