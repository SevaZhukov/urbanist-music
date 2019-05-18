package com.urbanist.music.feature.main.location_denied

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.urbanist.music.R
import kotlinx.android.synthetic.main.dialog_denied_permission_loc.*

class LocationDeniedDialog(context: Context) : Dialog(context) {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		requestWindowFeature(Window.FEATURE_NO_TITLE)
		setContentView(R.layout.dialog_denied_permission_loc)
		button.setOnClickListener {
			(context as Activity).finish()
		}
	}
}