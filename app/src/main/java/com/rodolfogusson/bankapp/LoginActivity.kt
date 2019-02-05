package com.rodolfogusson.bankapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

interface LoginActivityInput {
    fun displayLoginData(viewModel: LoginViewModel)
}

class LoginActivity : AppCompatActivity(), LoginActivityInput {

    lateinit var output: LoginInteractorInput
    lateinit var router: LoginRouter

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
    }

    companion object {
        const val TAG = "LoginActivity"
    }
}
