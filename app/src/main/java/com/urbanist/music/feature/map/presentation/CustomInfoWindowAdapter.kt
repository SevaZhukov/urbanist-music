package com.urbanist.music.feature.map.presentation

import android.app.Activity
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.urbanist.music.R
import com.urbanist.music.feature.map.domain.Event
import kotlinx.android.synthetic.main.window_custom.view.*
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast


class CustomInfoWindowAdapter(
    private val context: Activity,
    private val it: Event
) : GoogleMap.InfoWindowAdapter {
    override fun getInfoWindow(marker: Marker?): View? {
        return null
    }

    override fun getInfoContents(marker: Marker?): View {
        val v = context.layoutInflater.inflate(R.layout.window_custom, null)
        v.name.text = it.name
        v.nameBusker.text = it.buskerName
        v.genres.text = getFormattedList(it.genres)
        v.instruments.text = getFormattedList(it.instrument)
        v.propsText.text = it.props
        return v
    }

    private fun getFormattedList(genres: List<String>): String =
        genres
            .toString()
            .replace("[", "")
            .replace("]", "")
}