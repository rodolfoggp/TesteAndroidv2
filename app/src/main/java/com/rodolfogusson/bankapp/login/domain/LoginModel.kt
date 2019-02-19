package com.rodolfogusson.bankapp.login.domain

import com.rodolfogusson.bankapp.core.network.ApiResponse

data class LoginData(
    val login: String,
    var password: String)

data class User(
    val userId: Int,
    val name: String,
    val bankAccount: String,
    val agency: String,
    val balance: Double,
    val loginData: LoginData? = null)

class LoginResponse : ApiResponse() {
    val userAccount: User? = null
}

class LoginRequest{} //todo: USE this class

//class User == LoginResponse
