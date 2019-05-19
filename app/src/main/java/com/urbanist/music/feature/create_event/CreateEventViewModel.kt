package com.urbanist.music.feature.create_event

import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.urbanist.music.core.domain.PreferenceRepository
import com.urbanist.music.core.pref.Fields
import com.urbanist.music.core.presentation.BaseViewModel
import com.urbanist.music.feature.map.domain.Event
import java.util.*
import javax.inject.Inject

class CreateEventViewModel @Inject constructor(
    val preferenceRepository: PreferenceRepository,
    val firestore: FirebaseFirestore
) : BaseViewModel() {
    lateinit var eventsListener: CreateEventViewModel.EventsListener

    val name = MutableLiveData<String>()
    val title = MutableLiveData<String>()
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
        val busker = preferenceRepository.getBasker()
        name.value = busker.name
        props.value = busker.props
        title.value = ""
        strings.value = busker.instruments?.contains(Fields.STRINGS.title) ?: false
        drums.value = busker.instruments?.contains(Fields.DRUMS.title) ?: false
        wind.value = busker.instruments?.contains(Fields.WIND.title) ?: false
        folks.value = busker.instruments?.contains(Fields.FOLKS.title) ?: false
        classic.value = busker.instruments?.contains(Fields.CLASSIC.title) ?: false
        jazz.value = busker.instruments?.contains(Fields.JAZZ.title) ?: false
        pop.value = busker.instruments?.contains(Fields.POP.title) ?: false
        rock.value = busker.instruments?.contains(Fields.ROCK.title) ?: false
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
        if (title.value.isNullOrEmpty() || title.value.isNullOrBlank()) {
            eventsListener.showMessage("Заполни название события")
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
        val event = Event(
            title.value!!,
            busker.name!!,
            Date().time,
            Date().time + 10800000,
            location!!.latitude,
            location.longitude,
            busker.genres!!.toList(),
            busker.instruments!!.toList(),
            busker.props!!
        )
        firestore.collection("events")
            .add(event)
            .addOnSuccessListener {
                eventsListener.routeToMain()
            }
    }
}