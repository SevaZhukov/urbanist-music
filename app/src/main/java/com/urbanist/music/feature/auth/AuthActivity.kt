package com.urbanist.music.feature.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.urbanist.music.R
import com.urbanist.music.feature.main.MainActivity
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        buttonUrbanist.setOnClickListener {
            routeToMain()
        }
        buttonBusker.setOnClickListener {

        }
    }

    private fun routeToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun routeToAuth() {

    }
}