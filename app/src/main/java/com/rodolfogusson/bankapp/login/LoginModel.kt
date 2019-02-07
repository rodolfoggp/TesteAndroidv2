package com.rodolfogusson.bankapp.login

data class User(val login: String, var password: String)

data class LoginViewModel(var loginError: String, var passwordError: String)

class LoginRequest {

}

class LoginResponse {

}
