package com.rodolfogusson.bankapp.login.presentation

import com.rodolfogusson.bankapp.R
import com.rodolfogusson.bankapp.login.domain.LoginViewModel
import com.rodolfogusson.bankapp.login.domain.User
import com.rodolfogusson.bankapp.login.domain.Validation
import com.rodolfogusson.bankapp.login.domain.Validation.ValidationError.*
import java.lang.ref.WeakReference

interface LoginPresenterInput : LoginCallback, SavedUserCallback {
    fun presentValidationErrors(validation: Validation)
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
        val viewModel = LoginViewModel(user.loginData)
        output?.get()?.displayLastSavedUser(viewModel)
    }

    override fun presentValidationErrors(validation: Validation) {
        for (error in validation.errors) {
            presentValidationErrorFor(error)
        }
    }

    private fun presentValidationErrorFor(error: Validation.ValidationError) {
        when(error) {
            InvalidEmailOrCPF -> output?.get()?.displayUserError(R.string.invalid_user_error)
            InvalidPassword -> output?.get()?.displayPasswordError(R.string.invalid_password_error)
        }
    }

    companion object {
        const val TAG = "LoginPresenter"
    }
}
