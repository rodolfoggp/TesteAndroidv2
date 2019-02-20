package com.rodolfogusson.bankapp.core.network

abstract class ApiResponse(var error: ApiError? = null) {
    fun containsError(): Boolean {
        return !(error == null || error?.code == 0 || error?.message == "")
    }
}

class ApiError(var code: Int, var message: String)