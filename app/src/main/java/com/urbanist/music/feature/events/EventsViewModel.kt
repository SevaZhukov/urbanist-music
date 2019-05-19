package com.urbanist.music.feature.events

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.urbanist.music.core.presentation.BaseViewModel
import com.urbanist.music.feature.map.domain.Event
import com.urbanist.music.feature.map.domain.GetEventsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class EventsViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase
) : BaseViewModel() {

    val liveData = MutableLiveData<List<Event>>()

    override fun onBind(state: Bundle?) {
        super.onBind(state)

        getEventsUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveData.value = it
            }, {
                Log.i("MapsViewModel", it.localizedMessage)
            })
            .addTo(disposables)
    }
}