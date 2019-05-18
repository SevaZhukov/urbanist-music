package com.urbanist.music.feature.sign_up

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.urbanist.music.R
import com.urbanist.music.core.presentation.BaseActivity
import com.urbanist.music.databinding.ActivitySignUpBinding
import com.urbanist.music.feature.report.ReportViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SignUpActivity : BaseActivity<ActivitySignUpBinding>() {
    override val layoutId = R.layout.activity_sign_up

    @Inject
    lateinit var viewModel: SignUpViewModel

    override fun initBinding() {
        requireBinding().viewModel = viewModel
    }

    override fun initViewModel(state: Bundle?) {
        viewModel.onBind()
        viewModel.setEventListener(eventsListener)
    }

    private val eventsListener: SignUpViewModel.EventsListener =
        object : SignUpViewModel.EventsListener {
            override fun showMessage(message: String) {
                Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
            }
        }
}