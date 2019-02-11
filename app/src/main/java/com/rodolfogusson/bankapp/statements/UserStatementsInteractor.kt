package com.rodolfogusson.bankapp.statements

interface UserStatementsInteractorInput {
    fun fetchUserStatementsData(request: UserStatementsRequest)
}

class UserStatementsInteractor : UserStatementsInteractorInput {

    var output: UserStatementsPresenterInput? = null
    var workerInput: UserStatementsWorkerInput? = null
        get() {
            return field ?: UserStatementsWorker()
        }

    override fun fetchUserStatementsData(request: UserStatementsRequest) {
        // Log.d(TAG, "In method fetchUserStatementsData")
        val response = UserStatementsResponse()

        // Call the workers
        // repositoryInput.someWork()

        // Call the presenter
        output?.presentUserStatementsData(response)
    }

    companion object {
        const val TAG = "UserStatementsInteractor"
    }
}
