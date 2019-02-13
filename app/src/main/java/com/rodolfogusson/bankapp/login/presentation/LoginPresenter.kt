package com.rodolfogusson.bankapp.login.presentation

import com.rodolfogusson.bankapp.R
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
        output?.get()?.navigateToNextActivity()
    }

    override fun onLoginFailed(error: Throwable) {
        output?.get()?.displayLoginError(
            titleId = R.string.error_dialog_title,
            messageId = R.string.login_failed)
    }

    override fun onSavedUserFetched(user: User) {
        user.loginData?.let {
            output?.get()?.displayLastSavedUser(it)
        }
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
