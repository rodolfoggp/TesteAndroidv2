package com.rodolfogusson.bankapp.login.interactor

import androidx.test.espresso.idling.CountingIdlingResource
import com.rodolfogusson.bankapp.login.data.LoginRepository
import com.rodolfogusson.bankapp.login.data.LoginRepositoryInput
import com.rodolfogusson.bankapp.login.domain.LoginData
import com.rodolfogusson.bankapp.login.domain.LoginDataValidator
import com.rodolfogusson.bankapp.login.domain.LoginDataValidatorInput
import com.rodolfogusson.bankapp.login.presentation.LoginPresenterInput

interface LoginInteractorInput {
    fun sendLoginRequest(loginData: LoginData)
    fun fetchLastSavedUser()
    val idlingResource: CountingIdlingResource
}

class LoginInteractor : LoginInteractorInput {

    override val idlingResource = CountingIdlingResource("loginRequest")
    lateinit var output: LoginPresenterInput
    var validator: LoginDataValidatorInput = LoginDataValidator()
    var repository: LoginRepositoryInput = LoginRepository()

    override fun sendLoginRequest(loginData: LoginData) {
        val validation = validator.validate(loginData)
        if (validation.isValid) {
            repository.login(loginData, output)
        } else {
            output.presentValidationErrors(validation)
        }
    }

    override fun fetchLastSavedUser() {
        repository.getLastSavedUser(output)
    }

    companion object {
        const val TAG = "LoginInteractor"
    }
}
