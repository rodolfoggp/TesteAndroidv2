package com.rodolfogusson.bankapp.login.domain

data class LoginData(val login: String, var password: String)

data class LoginViewModel(val loginData: LoginData)

class User{}

class LoginValidationError

class LoginRequest{} //todo: USE this class

//class User == LoginResponse
