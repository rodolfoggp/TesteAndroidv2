package com.rodolfogusson.bankapp.login.presentation

import android.content.Intent
import com.rodolfogusson.bankapp.statements.UserStatementsActivity

import java.lang.ref.WeakReference

interface LoginRouterInput {
    fun determineNextScreen(): Intent
}

class LoginRouter(
    private val activity: WeakReference<LoginActivity>
) : LoginRouterInput {

    override fun determineNextScreen(): Intent {
        return Intent(activity.get(), UserStatementsActivity::class.java)
    }

    companion object {
        const val TAG = "HomeRouter"
    }
}
