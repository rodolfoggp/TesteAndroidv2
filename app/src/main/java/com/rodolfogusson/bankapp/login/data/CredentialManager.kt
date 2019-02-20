package com.rodolfogusson.bankapp.login.data

import android.content.SharedPreferences
import com.rodolfogusson.bankapp.login.domain.LoginData

class CredentialManager(private val sharedPreferences: SharedPreferences) {
    private val userKey = "USER_KEY"
    private val passwordKey = "PASSWORD_KEY"

    fun saveUserCredentials(loginData: LoginData){
        sharedPreferences.edit().putString(userKey, loginData.user).apply()
        sharedPreferences.edit().putString(passwordKey, loginData.password).apply()
    }

    fun getUserCredentials(): LoginData? {
        val user = sharedPreferences.getString(userKey, "")
        val password = sharedPreferences.getString(passwordKey, "")
        if (user != null && password != null) {
            return LoginData(user, password)
        }
        return null
    }

    companion object {
        val appPreferences = "BankAppPreferences"
    }
}
