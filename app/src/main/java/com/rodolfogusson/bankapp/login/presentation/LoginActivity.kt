package com.rodolfogusson.bankapp.login.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rodolfogusson.bankapp.R
import com.rodolfogusson.bankapp.login.domain.LoginData
import com.rodolfogusson.bankapp.login.interactor.LoginInteractorInput
import kotlinx.android.synthetic.main.activity_login.*

interface LoginActivityInput {
    fun displayLastSavedUser(loginData: LoginData)
    fun displayUserError(messageId: Int)
    fun displayPasswordError(messageId: Int)
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

    override fun displayLastSavedUser(loginData: LoginData) {
        userEditText.setText(loginData.login)
        passwordEditText.setText(loginData.password)
    }

    override fun displayUserError(messageId: Int) {
        userError.setText(R.string.invalid_user_error)
    }

    override fun displayPasswordError(messageId: Int) {
        passwordError.setText(R.string.invalid_password_error)
    }

    override fun onLoginSuccessful() {
        val nextActivity = router.determineNextScreen()
        startActivity(Intent(this, nextActivity::class.java))
    }

    fun onLoginButtonClicked(v: View) {
        clearFieldsErrors()
        val loginData = LoginData(userEditText.text.toString(), passwordEditText.text.toString())
        output.sendLoginRequest(loginData)
    }

    fun clearFieldsErrors() {
        userError.text = ""
        passwordError.text = ""
    }

    companion object {
        const val TAG = "LoginActivity"
    }
}
