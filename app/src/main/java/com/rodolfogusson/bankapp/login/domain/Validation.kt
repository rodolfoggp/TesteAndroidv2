package com.rodolfogusson.bankapp.login.domain

class Validation(val isValid: Boolean, val errors: ArrayList<ValidationError>) {
    enum class ValidationError {
        InvalidEmailOrCPF, InvalidPassword
    }
}