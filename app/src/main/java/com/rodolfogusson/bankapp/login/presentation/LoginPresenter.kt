package com.rodolfogusson.bankapp.login.presentation

import com.rodolfogusson.bankapp.login.domain.User
import com.rodolfogusson.bankapp.login.domain.Validation
import java.lang.ref.WeakReference

interface LoginPresenterInput {
    fun presentSavedUser(user: User)
    fun presentValidationError(validation: Validation)
    fun presentLoginResult(user: User)
}

class LoginPresenter : LoginPresenterInput {
    override fun presentLoginResult(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var output: WeakReference<LoginActivityInput>? = null

    override fun presentSavedUser(user: User) {
    }


    override fun presentValidationError(validation: Validation) {
    }

//    override fun presentLoginData(response: LoginResponse) {
//        // Log.d(TAG, "presentLoginData() called with: response = [$response]");
//        // Do your decoration or filtering here
//
//    }

    companion object {
        const val TAG = "LoginPresenter"
    }
}
