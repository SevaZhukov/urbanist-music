package com.urbanist.music.feature.events.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.urbanist.music.R
import com.urbanist.music.databinding.ItemEventBinding
import com.urbanist.music.feature.map.domain.Event

class EventsAdapter : RecyclerView.Adapter<EventsListViewHolder>() {


    var eventList: List<Event> = arrayListOf()

    val onEventsClickEvent = MutableLiveData<Event>()

    fun updateEvents(eventList: List<Event>) {
        this.eventList = eventList
        notifyDataSetChanged()
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


