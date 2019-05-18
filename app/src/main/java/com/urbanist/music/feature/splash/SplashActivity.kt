package com.urbanist.music.feature.splash

import android.Manifest
import android.content.Intent
import android.os.Bundle
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.urbanist.music.R
import com.urbanist.music.core.domain.PreferenceRepository
import com.urbanist.music.feature.auth.AuthActivity
import com.urbanist.music.feature.main.MainActivity
import com.urbanist.music.feature.main.location_denied.LocationDeniedDialog
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SplashActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var preferenceRepository: PreferenceRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        requestGeoAndCameraPermissions()
    }

    private fun routeToAuth() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun routeToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun isAuthorized(): Boolean {
        return preferenceRepository.getRole() != null
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
                        if (isAuthorized()) {
                            routeToMain()
                        } else {
                            routeToAuth()
                        }
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
        LocationDeniedDialog(this@SplashActivity).show()
    }
}