package com.urbanist.music.core.domain

import android.content.Context
import android.content.Context.MODE_PRIVATE
import javax.inject.Inject

class PreferenceRepository @Inject constructor(context: Context) {
    private val preferences = context.getSharedPreferences("preferences", MODE_PRIVATE)

    fun getRole(): String? {
        return preferences.getString(ROLE_KEY, null)
    }

    fun createBasker(
        name: String,
        props: String,
        instruments: MutableSet<String>,
        genres: MutableSet<String>
    ) {
        preferences.edit().putString(NAME_KEY, name)
            .putString(PROPS_KEY, props)
            .putStringSet(INSTRUMENTS_KEY, instruments)
            .putStringSet(GENRES_KEY, genres)
            .apply()
    }

    fun getBasker(): Busker {
        return Busker(
            preferences.getString(NAME_KEY, null),
            preferences.getString(PROPS_KEY, null),
            preferences.getStringSet(INSTRUMENTS_KEY, null),
            preferences.getStringSet(GENRES_KEY, null)
        )
    }

    fun setRole(role: String) {
        preferences.edit().putString(ROLE_KEY, role).apply()
    }

    companion object {
        const val ROLE_KEY = "role"
        const val NAME_KEY = "name"
        const val PROPS_KEY = "props"
        const val INSTRUMENTS_KEY = "instruments"
        const val GENRES_KEY = "genres"

        const val ROLE_NAME_BUSKER = "busker"
        const val ROLE_NAME_URBANIST = "urbanist"
    }
}