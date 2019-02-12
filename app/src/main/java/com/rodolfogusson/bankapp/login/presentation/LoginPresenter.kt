package com.rodolfogusson.bankapp.login.presentation

import com.rodolfogusson.bankapp.login.domain.User
import com.rodolfogusson.bankapp.login.domain.Validation
import java.lang.ref.WeakReference

interface LoginPresenterInput {
    fun presentSavedUser(user: User)
    fun presentValidationError(validation: Validation)
}

class LoginPresenter : LoginPresenterInput {
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
