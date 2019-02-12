package com.rodolfogusson.bankapp.login.data

import com.rodolfogusson.bankapp.login.domain.LoginData
import com.rodolfogusson.bankapp.login.presentation.LoginCallback
import com.rodolfogusson.bankapp.login.presentation.SavedUserCallback

interface LoginRepositoryInput {
    fun login(loginData: LoginData, callback: LoginCallback)
    fun getLastSavedUser(callback: SavedUserCallback)
}

class LoginRepository : LoginRepositoryInput {
    override fun getLastSavedUser(callback: SavedUserCallback) {
    }

    override fun login(loginData: LoginData, callback: LoginCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
