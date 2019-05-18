package com.urbanist.music.feature.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.urbanist.music.R
import com.urbanist.music.feature.main.MainActivity
import com.urbanist.music.feature.sign_up.SignUpActivity
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity: AppCompatActivity() {
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
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun routeToAuth() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }
}