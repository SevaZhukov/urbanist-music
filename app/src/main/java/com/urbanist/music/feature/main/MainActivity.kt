package com.urbanist.music.feature.main

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.urbanist.music.R
import com.urbanist.music.feature.report.ReportActivity
import com.urbanist.music.feature.rules.RulesActivity
import kotlinx.android.synthetic.main.activity_main.*
import com.urbanist.music.feature.main.location_denied.LocationDeniedDialog

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		rulesButton.setOnClickListener {
			startActivity(Intent(this, RulesActivity::class.java))
		}
		reportButton.setOnClickListener {
			requestGeoAndCameraPermissions()
		}
	}

	private fun requestGeoAndCameraPermissions() {
		Dexter.withActivity(this)
			.withPermissions(
				Manifest.permission.ACCESS_FINE_LOCATION,
				Manifest.permission.ACCESS_COARSE_LOCATION,
				Manifest.permission.CAMERA
			).withListener(object : MultiplePermissionsListener {
				override fun onPermissionsChecked(report: MultiplePermissionsReport) {
					if (report.deniedPermissionResponses.isEmpty().not()) {
						showDeniedLocationDialog()
					} else {
						startActivity(Intent(this@MainActivity, ReportActivity::class.java))
					}
				}

				override fun onPermissionRationaleShouldBeShown(
					permissions: List<PermissionRequest>,
					token: PermissionToken
				) {
					token.continuePermissionRequest()
				}
			}).withErrorListener {
				showDeniedLocationDialog()
			}.check()
	}

	private fun showDeniedLocationDialog() {
		LocationDeniedDialog(this@MainActivity).show()
	}
}
