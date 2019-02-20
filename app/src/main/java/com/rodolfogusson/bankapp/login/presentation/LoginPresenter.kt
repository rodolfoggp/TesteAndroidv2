package com.rodolfogusson.bankapp.login.presentation

import android.accounts.Account
import android.accounts.AccountManager
import com.rodolfogusson.bankapp.R
import com.rodolfogusson.bankapp.login.domain.LoginData
import com.rodolfogusson.bankapp.login.domain.User
import com.rodolfogusson.bankapp.login.domain.Validation
import com.rodolfogusson.bankapp.login.domain.Validation.ValidationError.*
import java.lang.ref.WeakReference

interface LoginPresenterInput : LoginCallback, SavedUserCallback {
    fun presentValidationErrors(validation: Validation)
}

interface LoginCallback {
    fun onLoginSuccessful()
    fun onLoginFailed(message: String? = null)
}

interface SavedUserCallback {
    fun onSavedUserFetched(loginData: LoginData)
}

class LoginPresenter(
    private val output: WeakReference<LoginActivityInput>
) : LoginPresenterInput {

    override fun onLoginSuccessful() {
        output.get()?.navigateToNextActivity()
    }

    override fun onLoginFailed(message: String?) {
        output.get()?.displayLoginError(message)
    }

    override fun onSavedUserFetched(loginData: LoginData) {
        output.get()?.displayLastSavedUser(loginData)
    }

    override fun presentValidationErrors(validation: Validation) {
        for (error in validation.errors) {
            presentValidationErrorFor(error)
        }
    }

    private fun presentValidationErrorFor(error: Validation.ValidationError) {
        when(error) {
            InvalidEmailOrCPF -> output.get()?.displayUserError(R.string.invalid_user_error)
            InvalidPassword -> output.get()?.displayPasswordError(R.string.invalid_password_error)
        }
    }

    companion object {
        const val TAG = "LoginPresenter"
    }
}
