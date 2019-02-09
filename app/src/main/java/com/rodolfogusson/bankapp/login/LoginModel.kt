package com.rodolfogusson.bankapp.login

data class LoginData(val login: String, var password: String)

data class LoginViewModel(val loginData: LoginData)

class LoginResponse{}

//class User == LoginResponse
