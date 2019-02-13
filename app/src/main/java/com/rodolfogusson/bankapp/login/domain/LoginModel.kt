package com.rodolfogusson.bankapp.login.domain

data class LoginData(
    val login: String,
    var password: String)

data class LoginViewModel(
    val loginData: LoginData? = null,
    val userError: String? = null,
    val passwordError: String? = null)

data class User(
    val userId: Int,
    val name: String,
    val bankAccount: String,
    val agency: String,
    val balance: Double,
    val loginData: LoginData? = null)

class LoginRequest{} //todo: USE this class

//class User == LoginResponse
