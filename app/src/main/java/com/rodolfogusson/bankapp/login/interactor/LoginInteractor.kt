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
    var output: LoginPresenterInput? = null
    var validator: LoginDataValidatorInput = LoginDataValidator()
    var repository: LoginRepositoryInput = LoginRepository()/* = LoginRepository*/
//        get() {
//            return field ?: LoginRepository()
//        }

    override fun sendLoginRequest(loginData: LoginData) {
        val validation = validator.validate(loginData)
        if (validation.isValid) {
            repository.login(loginData)
        } else {
            output?.presentValidationError(validation)
        }
    }

    override fun fetchLastSavedUser() {
        val user = repository.getLastSavedUser()
        output?.presentSavedUser(user)
    }
//    override fun fetchLoginData(request: LoginRequest) {
//        // Log.d(TAG, "In method fetchLoginData")
//        val response = LoginResponse()
//
//        // Call the workers
//        // repositoryInput.someWork()
//
//        // Call the presenter
//        output?.presentLoginData(response)
//    }

    companion object {
        const val TAG = "LoginInteractor"
    }
}
