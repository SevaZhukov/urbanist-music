package com.urbanist.music.core.domain

import android.content.Context
import android.content.Context.MODE_PRIVATE
import javax.inject.Inject

class PreferenceRepository @Inject constructor(context: Context) {
    private val preferences = context.getSharedPreferences("preferences", MODE_PRIVATE)

    fun getRole(): String? {
        return preferences.getString(ROLE_KEY, null)
    }

    companion object {
        const val ROLE_KEY = "role"

        const val ROLE_NAME_BUSKER = "busker"
        const val ROLE_NAME_URBANIST = "urbanist"
    }
}