package com.rodolfogusson.bankapp.login

import com.nhaarman.mockitokotlin2.*
import com.rodolfogusson.bankapp.core.network.services.BankService
import com.rodolfogusson.bankapp.login.data.LoginRepository
import com.rodolfogusson.bankapp.login.domain.LoginData
import com.rodolfogusson.bankapp.login.domain.LoginResponse
import com.rodolfogusson.bankapp.login.presentation.LoginCallback
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepositoryUnitTest {

    private lateinit var repository: LoginRepository
    private val loginDataMock = mock<LoginData>()
    private val loginCallbackMock = mock<LoginCallback>()
    private val callMock = mock<Call<LoginResponse>>()
    private val bankServiceMock = mock<BankService> { on { login(any()) } doReturn callMock }
    private val captor = argumentCaptor<Callback<LoginResponse>>()

    @Before
    fun setup() {
        repository = LoginRepository
        repository.bankService = bankServiceMock
    }

    @Test
    fun `repository is not null`() {
        assertNotNull(repository)
    }

    @Test
    fun `login request is made with the correct loginData`() {
        //GIVEN
        whenever(bankServiceMock.login(any())).thenReturn(callMock)

        //WHEN
        repository.login(loginDataMock, loginCallbackMock)

        //THEN
        verify(callMock).enqueue(captor.capture())
        verify(bankServiceMock).login(argThat { this == loginDataMock })
    }

    @Test
    fun `when login is successful, onLoginSuccessful is called`() {
        //GIVEN
        whenever(bankServiceMock.login(any())).thenReturn(callMock)
        doAnswer {
            val callback: Callback<LoginResponse> = it.getArgument(0)
            callback.onResponse(callMock, Response.success(LoginResponse()))
            return@doAnswer null
        }.whenever(callMock).enqueue(any())

        //WHEN
        repository.login(loginDataMock, loginCallbackMock)

        //THEN
        verify(loginCallbackMock).onLoginSuccessful()
    }

}