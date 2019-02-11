package com.rodolfogusson.bankapp.login.interactor

import androidx.test.espresso.idling.CountingIdlingResource
import com.rodolfogusson.bankapp.login.data.LoginRepository
import com.rodolfogusson.bankapp.login.data.LoginRepositoryInput
import com.rodolfogusson.bankapp.login.domain.LoginData
import com.rodolfogusson.bankapp.login.domain.Validator
import com.rodolfogusson.bankapp.login.presentation.LoginPresenterInput

interface LoginInteractorInput {
    fun sendLoginRequest(loginData: LoginData)
    fun fetchLastSavedUser()
    val idlingResource: CountingIdlingResource
}

class LoginInteractor : LoginInteractorInput {

    override val idlingResource = CountingIdlingResource("loginRequest")
    lateinit var output: LoginPresenterInput
    lateinit var validator: Validator
    var repositoryInput: LoginRepositoryInput? = null
        get() {
            return field ?: LoginRepository()
        }

    override fun sendLoginRequest(loginData: LoginData) {
        if (validator.validate(loginData)) {
            //do network
        } else {

        }
    }

    override fun fetchLastSavedUser() {

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
