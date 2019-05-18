package com.urbanist.music.feature.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.urbanist.music.R
import com.urbanist.music.core.domain.PreferenceRepository
import com.urbanist.music.feature.main.MainActivity
import com.urbanist.music.feature.sign_up.SignUpActivity
import dagger.android.DaggerService
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject

class AuthActivity: DaggerAppCompatActivity() {

    @Inject
    lateinit var preferenceRepository: PreferenceRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        buttonUrbanist.setOnClickListener {
            routeToMain()
        }
        buttonBusker.setOnClickListener {
            routeToAuth()
        }
    }

    private fun routeToMain() {
        preferenceRepository.setRole(PreferenceRepository.ROLE_NAME_URBANIST)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun routeToAuth() {
        val intent = Intent(this,   SignUpActivity::class.java)
        startActivity(intent)
    }
}