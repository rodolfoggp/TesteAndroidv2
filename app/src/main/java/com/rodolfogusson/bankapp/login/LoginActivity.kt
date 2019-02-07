package com.rodolfogusson.bankapp.login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rodolfogusson.bankapp.R
import kotlinx.android.synthetic.main.activity_login.*

interface LoginActivityInput {
    fun displayLoginData(viewModel: LoginViewModel)
}

class LoginActivity : AppCompatActivity(), LoginActivityInput {

    lateinit var output: LoginInteractorInput
    lateinit var router: LoginRouter
    lateinit var validator: LoginDataValidator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        LoginConfigurator.configureActivity(this)
        fetchData()
    }

    fun fetchData() {
        // create Request and set the needed input
        val request = LoginRequest()

        // Call the output to fetch the data
        output.fetchLoginData(request)
    }

    override fun displayLoginData(viewModel: LoginViewModel) {
        // Log.d(TAG, "displayLoginData() called with: viewModel = [$viewModel]")
        // Deal with the data, update the states, ui etc..
    }

    fun buttonClicked(v: View) {
        val userInfo = User(user.text.toString(), password.text.toString())
        validator.validate(userInfo)
    }

    companion object {
        const val TAG = "LoginActivity"
    }
}
