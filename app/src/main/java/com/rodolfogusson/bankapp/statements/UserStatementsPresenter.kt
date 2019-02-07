package com.rodolfogusson.bankapp.statements

import java.lang.ref.WeakReference

interface UserStatementsPresenterInput {
    fun presentUserStatementsData(response: UserStatementsResponse)
}

class UserStatementsPresenter : UserStatementsPresenterInput {

    var output: WeakReference<UserStatementsActivityInput>? = null

    override fun presentUserStatementsData(response: UserStatementsResponse) {
        // Log.d(TAG, "presentUserStatementsData() called with: response = [$response]");
        // Do your decoration or filtering here

    }

    companion object {
        const val TAG = "UserStatementsPresenter"
    }
}
