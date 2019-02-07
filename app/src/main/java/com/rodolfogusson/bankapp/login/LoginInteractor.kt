package com.rodolfogusson.bankapp.login

interface LoginInteractorInput {
    fun fetchLoginData(request: LoginRequest)
}

class LoginInteractor : LoginInteractorInput {

    var output: LoginPresenterInput? = null
    var workerInput: LoginWorkerInput? = null
        get() {
            return field ?: LoginWorker()
        }

    override fun fetchLoginData(request: LoginRequest) {
        // Log.d(TAG, "In method fetchLoginData")
        val response = LoginResponse()

        // Call the workers
        // workerInput.someWork()

        // Call the presenter
        output?.presentLoginData(response)
    }

    companion object {
        const val TAG = "LoginInteractor"
    }
}