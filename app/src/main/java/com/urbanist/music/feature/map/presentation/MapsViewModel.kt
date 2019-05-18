package com.urbanist.music.feature.map.presentation

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.urbanist.music.core.presentation.BaseViewModel
import com.urbanist.music.feature.map.domain.Event
import javax.inject.Inject

class MapsViewModel @Inject constructor() : BaseViewModel() {

    val liveData = MutableLiveData<List<Event>>()

    override fun onBind(state: Bundle?) {
        super.onBind(state)

        liveData.value = generateData()
    }


    private fun generateData(): List<Event> =
        listOf(
            Event(
                "someId",
                "Shrek",
                55.02933883666992,
                82.92640686035156,
                listOf("Rock", "Rap")
            ),
            Event(
                "someId",
                "Shrek2",
                54.02933883666992,
                83.92640686035156,
                listOf("Rock1", "Rap1")
            )
        )
}