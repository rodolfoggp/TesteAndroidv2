package com.rodolfogusson.bankapp.login.data

import com.rodolfogusson.bankapp.core.network.services.BankService
import com.rodolfogusson.bankapp.login.domain.LoginData
import com.rodolfogusson.bankapp.login.domain.LoginResponse
import com.rodolfogusson.bankapp.login.presentation.LoginCallback
import com.rodolfogusson.bankapp.login.presentation.SavedUserCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface LoginRepositoryInput {
    fun login(loginData: LoginData, callback: LoginCallback)
    fun getLastSavedUser(callback: SavedUserCallback)
}

object LoginRepository : LoginRepositoryInput {

    lateinit var bankService: BankService

    override fun login(loginData: LoginData, callback: LoginCallback) {
        bankService.login(loginData).enqueue(object : Callback<LoginResponse> {

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                callback.onLoginSuccessful()
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
            }
        })
    }

    override fun getLastSavedUser(callback: SavedUserCallback) {
    }
}
