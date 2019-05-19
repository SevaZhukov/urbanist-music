package com.urbanist.music.feature.map.presentation

import android.app.Activity
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.urbanist.music.R
import com.urbanist.music.feature.map.domain.Event
import kotlinx.android.synthetic.main.window_custom.view.*


class CustomInfoWindowAdapter(
    private val context: Activity,
    private val list: List<Event>
) : GoogleMap.InfoWindowAdapter {
    override fun getInfoWindow(marker: Marker?): View? {
        return null
    }

    override fun getInfoContents(marker: Marker?): View {
        val v = context.layoutInflater.inflate(R.layout.window_custom, null)
        var event: Event? = null
        list.forEach {
            if(it.name == marker?.title) {
                event = it
                return@forEach
            }
        }
        v.name.text = event!!.name
        v.nameBusker.text = event!!.buskerName
        v.genres.text = getFormattedList(event!!.genres)
        v.instruments.text = getFormattedList(event!!.instrument)
        v.propsText.text = event!!.props
        return v
    }

    private fun getFormattedList(genres: List<String>): String =
        genres
            .toString()
            .replace("[", "")
            .replace("]", "")
}