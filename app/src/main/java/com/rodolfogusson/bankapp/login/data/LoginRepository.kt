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
    lateinit var credentialManager: CredentialManager

    override fun login(loginData: LoginData, callback: LoginCallback) {
        bankService.login(loginData).enqueue(object : Callback<LoginResponse> {

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                response.body()?.let {
                    if (it.containsError()) {
                        callback.onLoginFailed(it.error?.message)
                    } else {
                        it.userAccount?.let { user ->
                            saveUserCredentials(loginData)
                            callback.onLoginSuccessful()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                callback.onLoginFailed(t.localizedMessage)
            }
        })
    }

    private fun saveUserCredentials(loginData: LoginData) {
        credentialManager.saveUserCredentials(loginData)
    }

    override fun getLastSavedUser(callback: SavedUserCallback) {
        credentialManager.getUserCredentials()?.let {
            callback.onSavedUserFetched(it)
        }
    }
}
