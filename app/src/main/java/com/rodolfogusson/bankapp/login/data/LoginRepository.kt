package com.rodolfogusson.bankapp.login.data

import com.rodolfogusson.bankapp.login.domain.LoginData
import com.rodolfogusson.bankapp.login.domain.User
import retrofit2.Call
import retrofit2.Callback

interface LoginRepositoryInput {
    fun login(loginData: LoginData, handler: (User) -> Unit)
    fun getLastSavedUser(): User
}

class LoginRepository : LoginRepositoryInput {
    override fun getLastSavedUser(): User {
        return User()
    }

    override fun login(loginData: LoginData, handler: (User) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
