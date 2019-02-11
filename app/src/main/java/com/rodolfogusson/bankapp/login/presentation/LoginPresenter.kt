package com.rodolfogusson.bankapp.login.presentation

import com.rodolfogusson.bankapp.login.domain.LoginResponse
import com.rodolfogusson.bankapp.login.domain.Validation
import java.lang.ref.WeakReference

interface LoginPresenterInput {
    fun presentLoginData(response: LoginResponse)
    fun sendValidationError(validation: Validation)
}

class LoginPresenter : LoginPresenterInput {
    var output: WeakReference<LoginActivityInput>? = null

    override fun sendValidationError(validation: Validation) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun presentLoginData(response: LoginResponse) {
        // Log.d(TAG, "presentLoginData() called with: response = [$response]");
        // Do your decoration or filtering here

    }

    companion object {
        const val TAG = "LoginPresenter"
    }
}
