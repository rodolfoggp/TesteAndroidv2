package com.rodolfogusson.bankapp.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rodolfogusson.bankapp.R
import kotlinx.android.synthetic.main.activity_login.*

interface LoginActivityInput {
    fun displayLoginData(viewModel: LoginViewModel)
    fun onLoginSuccessful()
}

class LoginActivity : AppCompatActivity(), LoginActivityInput {

    lateinit var output: LoginInteractorInput
    lateinit var router: LoginRouter
    lateinit var validator: Validator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        LoginConfigurator.configureActivity(this)
        fetchUserData()
    }

    fun fetchUserData() {
        output.fetchLastSavedUser()
    }

    override fun displayLoginData(viewModel: LoginViewModel) {
        // Log.d(TAG, "displayLoginData() called with: viewModel = [$viewModel]")
        // Deal with the data, update the states, ui etc..
    }

    override fun onLoginSuccessful() {
        val nextActivity = router.determineNextScreen()
        startActivity(Intent(this, nextActivity::class.java))
    }

    fun buttonClicked(v: View) {
        val user = User(user.text.toString(), password.text.toString())
        if (validator.validate(user)) output.sendLoginRequest(user)
    }

//    fun goToNextActitivy() {
//        val nextActivity = router.determineNextScreen()
//        startActivity(Intent(this, nextActivity::class.java))
//    }

    companion object {
        const val TAG = "LoginActivity"
    }
}
