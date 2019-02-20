package com.rodolfogusson.bankapp.login

import com.nhaarman.mockitokotlin2.*
import com.rodolfogusson.bankapp.core.network.ApiError
import com.rodolfogusson.bankapp.core.network.services.BankService
import com.rodolfogusson.bankapp.login.data.CredentialManager
import com.rodolfogusson.bankapp.login.data.LoginRepository
import com.rodolfogusson.bankapp.login.domain.LoginData
import com.rodolfogusson.bankapp.login.domain.LoginResponse
import com.rodolfogusson.bankapp.login.domain.User
import com.rodolfogusson.bankapp.login.presentation.LoginCallback
import com.rodolfogusson.bankapp.login.presentation.SavedUserCallback
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException

@RunWith(RobolectricTestRunner::class)
class LoginRepositoryUnitTest {

    private lateinit var repository: LoginRepository
    private val loginCallbackMock = mock<LoginCallback>()
    private val savedUserCallbackMock = mock<SavedUserCallback>()
    private val callMock = mock<Call<LoginResponse>>()
    private val bankServiceMock = mock<BankService> { on { login(any()) } doReturn callMock }
    private val captor = argumentCaptor<Callback<LoginResponse>>()
    private val loginDataMock = LoginData("johndoe", "123456789")
    private val userMock = User(1, "john", "123", "12", 12.00, loginDataMock)
    private val loginResponseMock = LoginResponse(userMock)
    private val credentialManagerMock = mock<CredentialManager> {
        on { getUserCredentials() } doReturn loginDataMock
    }

    @Before
    fun setUp() {
        repository = LoginRepository
        repository.bankService = bankServiceMock
        repository.credentialManager = credentialManagerMock
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
            callback.onResponse(callMock, Response.success(loginResponseMock))
            return@doAnswer null
        }.whenever(callMock).enqueue(any())

        //WHEN
        repository.login(loginDataMock, loginCallbackMock)

        //THEN
        verify(loginCallbackMock).onLoginSuccessful()
    }

    @Test
    fun `when login fails, onLoginFailed is called`() {
        //GIVEN
        whenever(bankServiceMock.login(any())).thenReturn(callMock)
        doAnswer {
            val callback: Callback<LoginResponse> = it.getArgument(0)
            callback.onFailure(callMock, SocketTimeoutException())
            return@doAnswer null
        }.whenever(callMock).enqueue(any())

        //WHEN
        repository.login(loginDataMock, loginCallbackMock)

        //THEN
        verify(loginCallbackMock).onLoginFailed()
    }

    @Test
    fun `when login fails and receives an error message, onLoginFailed is called with this message`() {
        //GIVEN
        val message = "Usuário ou senha incorreta"
        whenever(bankServiceMock.login(any())).thenReturn(callMock)
        doAnswer {
            val callback: Callback<LoginResponse> = it.getArgument(0)
            loginResponseMock.error = ApiError(53, message)
            callback.onResponse(callMock, Response.success(loginResponseMock))
            return@doAnswer null
        }.whenever(callMock).enqueue(any())

        //WHEN
        repository.login(loginDataMock, loginCallbackMock)

        //THEN
        verify(loginCallbackMock).onLoginFailed(message)
    }

    @Test
    fun `when login is successful, save user credentials`() {
        //GIVEN
        whenever(bankServiceMock.login(any())).thenReturn(callMock)
        doAnswer {
            val callback: Callback<LoginResponse> = it.getArgument(0)
            callback.onResponse(callMock, Response.success(loginResponseMock))
            return@doAnswer null
        }.whenever(callMock).enqueue(any())

        //WHEN
        repository.login(loginDataMock, loginCallbackMock)

        //THEN
        verify(credentialManagerMock).saveUserCredentials(loginDataMock)
    }

    @Test
    fun `if there is a saved user, onSavedUserFetched is called`() {
        //WHEN
        repository.getLastSavedUser(savedUserCallbackMock)

        //THEN
        verify(savedUserCallbackMock).onSavedUserFetched(any())
    }
}