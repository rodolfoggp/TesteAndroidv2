package com.rodolfogusson.bankapp.login.data

import com.rodolfogusson.bankapp.login.domain.LoginData

interface LoginRepositoryInput {
    fun login(loginData: LoginData)
}

class LoginRepository : LoginRepositoryInput {
    override fun login(loginData: LoginData) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
