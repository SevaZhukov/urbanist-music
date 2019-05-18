package com.urbanist.music.feature.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.urbanist.music.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
		NavigationUI.setupWithNavController(bottomNavigationView, navController)
	}
}
