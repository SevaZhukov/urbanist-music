package com.urbanist.music.feature.sign_up

import android.graphics.Bitmap
import android.location.Location
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.urbanist.music.core.domain.PreferenceRepository
import com.urbanist.music.core.pref.Fields
import com.urbanist.music.core.presentation.BaseViewModel
import javax.inject.Inject

class SignUpViewModel @Inject constructor(val preferenceRepository: PreferenceRepository) : BaseViewModel() {
    lateinit var eventsListener: SignUpViewModel.EventsListener

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
    }

    init {
        name.value = ""
        props.value = ""
        strings.value = false
        drums.value = false
        wind.value = false
        folks.value = false
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
        eventsListener.routeToMain()
    }
}