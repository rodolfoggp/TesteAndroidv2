package com.rodolfogusson.bankapp.login.presentation

import com.rodolfogusson.bankapp.login.domain.User
import com.rodolfogusson.bankapp.login.domain.Validation
import java.lang.ref.WeakReference

interface LoginPresenterInput : LoginCallback, SavedUserCallback {
    fun presentSavedUser(user: User)
    fun presentValidationError(validation: Validation)
}

interface LoginCallback {
    fun onLoginSuccessful()
    fun onLoginFailed(error: Throwable)
}

interface SavedUserCallback {
    fun onSavedUserFetched(user: User)
}

class LoginPresenter : LoginPresenterInput {

    var output: WeakReference<LoginActivityInput>? = null

    override fun onLoginSuccessful() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoginFailed(error: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSavedUserFetched(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

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
