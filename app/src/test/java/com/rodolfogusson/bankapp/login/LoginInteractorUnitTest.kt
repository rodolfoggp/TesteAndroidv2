package com.rodolfogusson.bankapp.login

import com.nhaarman.mockitokotlin2.*
import com.rodolfogusson.bankapp.login.data.LoginRepositoryInput
import com.rodolfogusson.bankapp.login.domain.LoginData
import com.rodolfogusson.bankapp.login.domain.LoginDataValidatorInput
import com.rodolfogusson.bankapp.login.domain.Validation
import com.rodolfogusson.bankapp.login.interactor.LoginInteractor
import com.rodolfogusson.bankapp.login.presentation.LoginPresenterInput
import com.rodolfogusson.bankapp.login.domain.Validation.ValidationError.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LoginInteractorUnitTest {

    private lateinit var interactor: LoginInteractor
    private val loginDataMock = mock<LoginData>()
    private val presenterMock = mock<LoginPresenterInput>()
    private val repositoryMock = mock<LoginRepositoryInput>()
    private val validatorMock = mock<LoginDataValidatorInput>()

    @Before
    fun setUp() {
        interactor = LoginInteractor(presenterMock, validatorMock, repositoryMock)
    }

    @Test
    fun `when sendLoginRequest is called, login data is validated`() {
        //GIVEN
        whenever(validatorMock.validate(any())).thenReturn(Validation(true, ArrayList()))

        //WHEN
        interactor.sendLoginRequest(loginDataMock)

        //THEN
        verify(validatorMock).validate(any())
    }

    @Test
    fun `when sendLoginRequest is called and login data is valid, a login request is sent`() {
        //GIVEN
        whenever(validatorMock.validate(any())).thenReturn(Validation(true, ArrayList()))

        //WHEN
        interactor.sendLoginRequest(loginDataMock)

        //THEN
        verify(repositoryMock).login(any(), any())
    }

    @Test
    fun `sendLoginRequest calls login request with correct data and callback`() {
        //GIVEN
        whenever(validatorMock.validate(any())).thenReturn(Validation(true, ArrayList()))

        //WHEN
        interactor.sendLoginRequest(loginDataMock)

        //THEN
        verify(repositoryMock).login(argThat { this == loginDataMock }, argThat { this == presenterMock })
    }

    @Test
    fun `when login data validation fails, presentValidationError is called`() {
        //GIVEN
        whenever(validatorMock.validate(any())).thenReturn(Validation(false, ArrayList()))

        //WHEN
        interactor.sendLoginRequest(loginDataMock)

        //THEN
        verify(presenterMock).presentValidationErrors(any())
    }

    @Test
    fun `when login data validation fails, the right error is passed to the presenter`() {
        //GIVEN
        val errorsList = arrayListOf(InvalidEmailOrCPF, InvalidPassword)
        whenever(validatorMock.validate(any())).thenReturn(Validation(false, errorsList))

        //WHEN
        interactor.sendLoginRequest(loginDataMock)

        //THEN
        verify(presenterMock).presentValidationErrors(
            argThat { this.errors == errorsList })
    }

    @Test
    fun `fetchLastSavedUser gets the user from LoginRepository`() {
        //WHEN
        interactor.fetchLastSavedUser()

        //THEN
        verify(repositoryMock).getLastSavedUser(any())
    }

    @Test
    fun `fetchLastSavedUser is called with the correct callback`() {
        //WHEN
        interactor.fetchLastSavedUser()

        //THEN
        verify(repositoryMock).getLastSavedUser(
            argThat { this == presenterMock })
    }
}
