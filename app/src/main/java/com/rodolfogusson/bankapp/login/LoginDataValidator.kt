package com.rodolfogusson.bankapp.login

interface Validator {
    fun validate(user: User): Boolean
}

class LoginDataValidator : Validator{

    override fun validate(user: User): Boolean {
        return false
    }
}