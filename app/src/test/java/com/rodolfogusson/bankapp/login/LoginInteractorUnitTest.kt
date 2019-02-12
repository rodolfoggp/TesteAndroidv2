package com.rodolfogusson.bankapp.login

import com.nhaarman.mockitokotlin2.*
import com.rodolfogusson.bankapp.login.data.LoginRepositoryInput
import com.rodolfogusson.bankapp.login.domain.LoginData
import com.rodolfogusson.bankapp.login.domain.LoginDataValidatorInput
import com.rodolfogusson.bankapp.login.domain.User
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
    private val userMock = mock<User>()

    @Before
    fun setup() {
        interactor = LoginInteractor()
    }

    @Test
    fun `when sendLoginRequest is called, login data is validated`() {
        //GIVEN
        val repositoryMock = mock<LoginRepositoryInput>()
        val validatorMock = mock<LoginDataValidatorInput> {
            on { validate(any()) } doReturn Validation(true, ArrayList())
        }
        interactor.validator = validatorMock
        interactor.repository = repositoryMock

        //WHEN
        interactor.sendLoginRequest(loginDataMock)

        //THEN
        verify(validatorMock).validate(any())
    }

    @Test
    fun `when sendLoginRequest is called and login data is valid, a login request is sent`() {
        //GIVEN
        val repositorySpy = spy<LoginRepositoryInput>()
        val validatorMock = mock<LoginDataValidatorInput> {
            on { validate(any()) } doReturn Validation(true, ArrayList())
        }
        interactor.validator = validatorMock
        interactor.repository = repositorySpy

        //WHEN
        interactor.sendLoginRequest(loginDataMock)

        //THEN
        verify(repositorySpy).login(any(), any())
    }

    @Test
    fun `sendLoginRequest calls login request with correct data and handler`() {
        //GIVEN
        val repositorySpy = spy<LoginRepositoryInput>()
        val validatorMock = mock<LoginDataValidatorInput> {
            on { validate(any()) } doReturn Validation(true, ArrayList())
        }
        interactor.validator = validatorMock
        interactor.repository = repositorySpy

        //WHEN
        interactor.sendLoginRequest(loginDataMock)

        //THEN
        verify(repositorySpy).login(argThat { this == loginDataMock }, argThat { this == interactor::handleLogin })
    }

    @Test
    fun `handleLogin should call presentLoginResult`() {
        //GIVEN
        val presenterSpy = spy<LoginPresenterInput>()
        interactor.output = presenterSpy

        //WHEN
        interactor.handleLogin(userMock)

        //THEN
        verify(presenterSpy).presentLoginResult(any())
    }

    @Test
    fun `presentLoginResult is called with the right input`() {
        //GIVEN
        val presenterSpy = spy<LoginPresenterInput>()
        interactor.output = presenterSpy

        //WHEN
        interactor.handleLogin(userMock)

        //THEN
        verify(presenterSpy).presentLoginResult(argThat { this == userMock })
    }

    @Test
    fun `when login data validation fails, presentValidationError is called`() {
        //GIVEN
        val presenterSpy = spy<LoginPresenterInput>()
        val validatorMock = mock<LoginDataValidatorInput> {
            on { validate(any()) } doReturn Validation(false, ArrayList())
        }
        interactor.validator = validatorMock
        interactor.output = presenterSpy

        //WHEN
        interactor.sendLoginRequest(loginDataMock)

        //THEN
        verify(presenterSpy).presentValidationError(any())
    }

    @Test
    fun `when login data validation fails, the right error is passed to the presenter`() {
        //GIVEN
        val errorsList = arrayListOf(InvalidEmailOrCPF, InvalidPassword)
        val presenterSpy = spy<LoginPresenterInput>()
        val validatorMock = mock<LoginDataValidatorInput> {
            on { validate(any()) } doReturn Validation(false, errorsList)
        }
        interactor.validator = validatorMock
        interactor.output = presenterSpy

        //WHEN
        interactor.sendLoginRequest(loginDataMock)

        //THEN
        verify(presenterSpy).presentValidationError(
            argThat { this.errors == errorsList })
    }

    @Test
    fun `fetchLastSavedUser gets the user from LoginRepository`() {
        //GIVEN
        val repositorySpy = spy<LoginRepositoryInput>()
        interactor.repository = repositorySpy

        //WHEN
        interactor.fetchLastSavedUser()

        //THEN
        verify(repositorySpy).getLastSavedUser()
    }

    @Test
    fun `fetchLastSavedUser calls presentSavedUser`() {
        //GIVEN
        val presenterSpy = spy<LoginPresenterInput>()
        val repositoryMock = mock<LoginRepositoryInput> {
            on { getLastSavedUser() } doReturn userMock
        }
        interactor.repository = repositoryMock
        interactor.output = presenterSpy

        //WHEN
        interactor.fetchLastSavedUser()

        //THEN
        verify(presenterSpy).presentSavedUser(any())
    }

    @Test
    fun `when fetchLastSavedUser is called, the right user is passed to the presenter`() {
        //GIVEN
        val presenterSpy = spy<LoginPresenterInput>()
        val repositoryMock = mock<LoginRepositoryInput> {
            on { getLastSavedUser() } doReturn userMock
        }
        interactor.repository = repositoryMock
        interactor.output = presenterSpy

        //WHEN
        interactor.fetchLastSavedUser()

        //THEN
        verify(presenterSpy).presentSavedUser(
            argThat { this == userMock })
    }
}
