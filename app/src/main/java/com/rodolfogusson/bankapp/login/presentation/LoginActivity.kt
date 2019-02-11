package com.rodolfogusson.bankapp.login.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rodolfogusson.bankapp.R
import com.rodolfogusson.bankapp.login.domain.LoginData
import com.rodolfogusson.bankapp.login.domain.LoginViewModel
import com.rodolfogusson.bankapp.login.interactor.LoginInteractorInput
import kotlinx.android.synthetic.main.activity_login.*

interface LoginActivityInput {
    fun displayLastSavedUser(viewModel: LoginViewModel)
    fun onLoginSuccessful()
}

class LoginActivity : AppCompatActivity(), LoginActivityInput {

    lateinit var output: LoginInteractorInput
    lateinit var router: LoginRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        LoginConfigurator.configureActivity(this)
        fetchUserData()
    }

    fun fetchUserData() {
        output.fetchLastSavedUser()
    }

    override fun displayLastSavedUser(viewModel: LoginViewModel) {
        userEditText.setText(viewModel.loginData.login)
        passwordEditText.setText(viewModel.loginData.password)
    }

    override fun onLoginSuccessful() {
        val nextActivity = router.determineNextScreen()
        startActivity(Intent(this, nextActivity::class.java))
    }

    fun buttonClicked(v: View) {
        val loginData =
            LoginData(
                userEditText.text.toString(),
                passwordEditText.text.toString()
            )
        output.sendLoginRequest(loginData)
    }

    companion object {
        const val TAG = "LoginActivity"
    }
}
