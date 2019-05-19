package com.urbanist.music.feature.create_event

import android.graphics.Bitmap
import android.location.Location
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.urbanist.music.core.domain.PreferenceRepository
import com.urbanist.music.core.pref.Fields
import com.urbanist.music.core.presentation.BaseViewModel
import com.urbanist.music.feature.map.domain.Event
import com.urbanist.music.feature.map.domain.EventsRepository
import com.urbanist.music.feature.map.domain.GetEventsUseCase
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class CreateEventViewModel @Inject constructor(
    val preferenceRepository: PreferenceRepository,
    val firestore: FirebaseFirestore
) : BaseViewModel() {
    lateinit var eventsListener: CreateEventViewModel.EventsListener

    val name = MutableLiveData<String>()
    val props = MutableLiveData<String>()
    val strings = MutableLiveData<Boolean>()
    val drums = MutableLiveData<Boolean>()
    val wind = MutableLiveData<Boolean>()
    val folks = MutableLiveData<Boolean>()
    val classic = MutableLiveData<Boolean>()
    val jazz = MutableLiveData<Boolean>()
    val pop = MutableLiveData<Boolean>()
    val rock = MutableLiveData<Boolean>()

    fun setEventListener(eventsListener: EventsListener) {
        this.eventsListener = eventsListener
    }

    interface EventsListener {
        fun showMessage(message: String)
        fun routeToMain()
        fun getLocation(): Location?
    }

    init {
        name.value = ""
        props.value = ""
        strings.value = false
        drums.value = false
        wind.value = false
        folks.value = false
        strings.value = false
        classic.value = false
        jazz.value = false
        pop.value = false
        rock.value = false
    }

    fun onSignUpClick() {
        if ((strings.value!! || drums.value!! || wind.value!! || folks.value!!).not()) {
            eventsListener.showMessage("Выбери хотя бы один вид инструментов")
            return
        }
        if ((classic.value!! || jazz.value!! || pop.value!! || rock.value!!).not()) {
            eventsListener.showMessage("Выбери хотя бы один жанр")
            return
        }
        if (name.value.isNullOrEmpty() || name.value.isNullOrBlank()) {
            eventsListener.showMessage("Заполни имя")
            return
        }
        if (props.value.isNullOrEmpty() || props.value.isNullOrBlank()) {
            eventsListener.showMessage("Заполни реквизиты")
            return
        }
        val instruments = mutableSetOf<String>()
        val genres = mutableSetOf<String>()
        if (strings.value!!) instruments.add(Fields.STRINGS.title)
        if (drums.value!!) instruments.add(Fields.DRUMS.title)
        if (wind.value!!) instruments.add(Fields.WIND.title)
        if (folks.value!!) instruments.add(Fields.FOLKS.title)
        if (classic.value!!) genres.add(Fields.CLASSIC.title)
        if (jazz.value!!) genres.add(Fields.JAZZ.title)
        if (pop.value!!) genres.add(Fields.POP.title)
        if (rock.value!!) genres.add(Fields.ROCK.title)
        preferenceRepository.createBasker(name.value!!, props.value!!, instruments, genres)
        preferenceRepository.setRole(PreferenceRepository.ROLE_NAME_BUSKER)
        val busker = preferenceRepository.getBasker()
        val location = eventsListener.getLocation()
        busker.latitude = location?.latitude.toString()
        busker.longitude = location?.longitude.toString()
        firestore.collection("events")
            .add(busker)
            .addOnSuccessListener {
                eventsListener.routeToMain()
            }
    }
}