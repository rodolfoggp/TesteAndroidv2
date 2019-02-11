package com.rodolfogusson.bankapp.login.presentation

import com.rodolfogusson.bankapp.login.domain.LoginResponse
import java.lang.ref.WeakReference

interface LoginPresenterInput {
    fun presentLoginData(response: LoginResponse)
}

class LoginPresenter : LoginPresenterInput {

    var output: WeakReference<LoginActivityInput>? = null

    override fun presentLoginData(response: LoginResponse) {
        // Log.d(TAG, "presentLoginData() called with: response = [$response]");
        // Do your decoration or filtering here

    }

    companion object {
        const val TAG = "LoginPresenter"
    }
}
