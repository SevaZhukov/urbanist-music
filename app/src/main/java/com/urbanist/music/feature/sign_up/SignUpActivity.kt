package com.urbanist.music.feature.sign_up

import android.os.Bundle
import com.urbanist.music.R
import com.urbanist.music.core.presentation.BaseActivity
import com.urbanist.music.databinding.ActivitySignUpBinding
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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }

    private val eventsListener: SignUpViewModel.EventsListener =
        object : SignUpViewModel.EventsListener {
            override fun showMessage(message: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onUnbind()
    }
}