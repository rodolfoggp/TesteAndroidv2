package com.rodolfogusson.bankapp.core.network.services

import com.rodolfogusson.bankapp.login.domain.LoginData
import com.rodolfogusson.bankapp.login.domain.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BankService {
    @POST("login")
    fun login(@Body loginData: LoginData): Call<LoginResponse>

//    @POST("transaction")
//    fun sendPaymentRequest(@Body paymentRequest: PaymentRequest): Call<PaymentResponse>
}