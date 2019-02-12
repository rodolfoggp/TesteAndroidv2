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

    @Before
    fun setup() {
        interactor = LoginInteractor()
    }

    @Test
    fun `when sendLoginRequest is called, login data is validated`() {
        //GIVEN
        val validatorMock = mock<LoginDataValidatorInput> {
            on { validate(any()) } doReturn Validation(true, ArrayList())
        }
        interactor.validator = validatorMock
        interactor.repository = repositoryMock
        interactor.output = presenterMock

        //WHEN
        interactor.sendLoginRequest(loginDataMock)

        //THEN
        verify(validatorMock).validate(any())
    }

    @Test
    fun `when sendLoginRequest is called and login data is valid, a login request is sent`() {
        //GIVEN
        val validatorMock = mock<LoginDataValidatorInput> {
            on { validate(any()) } doReturn Validation(true, ArrayList())
        }
        interactor.validator = validatorMock
        interactor.repository = repositoryMock
        interactor.output = presenterMock

        //WHEN
        interactor.sendLoginRequest(loginDataMock)

        //THEN
        verify(repositoryMock).login(any(), any())
    }

    @Test
    fun `sendLoginRequest calls login request with correct data and callback`() {
        //GIVEN
        val validatorMock = mock<LoginDataValidatorInput> {
            on { validate(any()) } doReturn Validation(true, ArrayList())
        }
        interactor.validator = validatorMock
        interactor.repository = repositoryMock
        interactor.output = presenterMock

        //WHEN
        interactor.sendLoginRequest(loginDataMock)

        //THEN
        verify(repositoryMock).login(argThat { this == loginDataMock }, argThat { this == presenterMock })
    }

    @Test
    fun `when login data validation fails, presentValidationError is called`() {
        //GIVEN
        val validatorMock = mock<LoginDataValidatorInput> {
            on { validate(any()) } doReturn Validation(false, ArrayList())
        }
        interactor.validator = validatorMock
        interactor.output = presenterMock

        //WHEN
        interactor.sendLoginRequest(loginDataMock)

        //THEN
        verify(presenterMock).presentValidationError(any())
    }

    @Test
    fun `when login data validation fails, the right error is passed to the presenter`() {
        //GIVEN
        val errorsList = arrayListOf(InvalidEmailOrCPF, InvalidPassword)
        val validatorMock = mock<LoginDataValidatorInput> {
            on { validate(any()) } doReturn Validation(false, errorsList)
        }
        interactor.validator = validatorMock
        interactor.output = presenterMock

        //WHEN
        interactor.sendLoginRequest(loginDataMock)

        //THEN
        verify(presenterMock).presentValidationError(
            argThat { this.errors == errorsList })
    }

    @Test
    fun `fetchLastSavedUser gets the user from LoginRepository`() {
        //GIVEN
        interactor.repository = repositoryMock
        interactor.output = presenterMock

        //WHEN
        interactor.fetchLastSavedUser()

        //THEN
        verify(repositoryMock).getLastSavedUser(any())
    }

    @Test
    fun `fetchLastSavedUser is called with the correct callback`() {
        //GIVEN
        interactor.repository = repositoryMock
        interactor.output = presenterMock

        //WHEN
        interactor.fetchLastSavedUser()

        //THEN
        verify(repositoryMock).getLastSavedUser(
            argThat { this == presenterMock })
    }
}
