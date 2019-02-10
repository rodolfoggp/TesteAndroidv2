package com.rodolfogusson.bankapp.login

import androidx.test.espresso.idling.CountingIdlingResource

interface LoginInteractorInput {
    fun sendLoginRequest(loginData: LoginData)
    fun fetchLastSavedUser()
    val idlingResource: CountingIdlingResource
}

class LoginInteractor : LoginInteractorInput {

    override val idlingResource = CountingIdlingResource("loginRequest")
    var output: LoginPresenterInput? = null
    var workerInput: LoginWorkerInput? = null
        get() {
            return field ?: LoginWorker()
        }

    override fun sendLoginRequest(loginData: LoginData) {

    }

    override fun fetchLastSavedUser() {

    }
//    override fun fetchLoginData(request: LoginRequest) {
//        // Log.d(TAG, "In method fetchLoginData")
//        val response = LoginResponse()
//
//        // Call the workers
//        // workerInput.someWork()
//
//        // Call the presenter
//        output?.presentLoginData(response)
//    }

    companion object {
        const val TAG = "LoginInteractor"
    }
}
