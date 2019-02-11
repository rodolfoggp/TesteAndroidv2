package com.rodolfogusson.bankapp.login

import com.nhaarman.mockitokotlin2.*
import com.rodolfogusson.bankapp.login.data.LoginRepositoryInput
import com.rodolfogusson.bankapp.login.domain.LoginData
import com.rodolfogusson.bankapp.login.domain.LoginDataValidatorInput
import com.rodolfogusson.bankapp.login.domain.Validation
import com.rodolfogusson.bankapp.login.interactor.LoginInteractor
import com.rodolfogusson.bankapp.login.presentation.LoginPresenterInput
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LoginInteractorUnitTest {

    private lateinit var interactor: LoginInteractor
    private val fakeLoginData = LoginData("","")

    @Before
    fun setup() {
        interactor = LoginInteractor()
    }

    @Test
    fun `when sendLoginRequest is called, login data is validated`() {
        //GIVEN
        val validatorMock = mock<LoginDataValidatorInput>()
        val repositoryMock = mock<LoginRepositoryInput>()
        interactor.validator = validatorMock
        interactor.repository = repositoryMock

        //WHEN
        whenever(validatorMock.validate(any())).thenReturn(Validation(true, ArrayList()))
        interactor.sendLoginRequest(fakeLoginData)

        //THEN
        verify(validatorMock, times(1)).validate(any())
    }

    @Test
    fun `when sendLoginRequest is called and login data is valid, a login request is sent`() {
        //GIVEN
        val validatorMock = mock<LoginDataValidatorInput>()
        val repositorySpy = spy<LoginRepositoryInput>()
        interactor.validator = validatorMock
        interactor.repository = repositorySpy

        //WHEN
        whenever(validatorMock.validate(any())).thenReturn(Validation(true, ArrayList()))
        interactor.sendLoginRequest(fakeLoginData)

        //THEN
        verify(repositorySpy, times(1)).login(any())
    }

    @Test
    fun `when login data validation fails, presenter calls sendValidationError`() {
        //GIVEN
        val validatorMock = mock<LoginDataValidatorInput>()
        val presenterSpy = spy<LoginPresenterInput>()
        interactor.validator = validatorMock
        interactor.output = presenterSpy

        //WHEN
        whenever(validatorMock.validate(any())).thenReturn(Validation(false, ArrayList()))
        interactor.sendLoginRequest(fakeLoginData)

        //THEN
        verify(presenterSpy, times(1)).sendValidationError(any())
    }

//    @Test
//    fun `fetchLastSavedUser gets the user from UserRepository`() {
//        //WHEN
//        interactor.fetchLastSavedUser()
//
//        //THEN
//
//    }

//    @Test
//    fun fetchLoginData_with_validInput_shouldCall_presentLoginData() {
//        // Given
//        val request = LoginRequest()
//        //homeRequest.isFutureTrips = true
//        val presenterInputSpy = LoginPresenterInputSpy()
//        interactor.output = presenterInputSpy
//        // When
//        interactor.fetchLoginData(request)
//
//        // Then
//        Assert.assertTrue(
//            "When the isValid input is passed to LoginInteractor "
//                    + "Then presentLoginData should be called",
//            presenterInputSpy.presentLoginDataIsCalled
//        )
//    }

//    private inner class LoginPresenterInputSpy : LoginPresenterInput {
//
//        internal var presentLoginDataIsCalled = false
//        internal var responseCopy: LoginResponse? = null
//        override fun presentLoginData(response: LoginResponse) {
//            presentLoginDataIsCalled = true
//            responseCopy = response
//        }
//    }


}
