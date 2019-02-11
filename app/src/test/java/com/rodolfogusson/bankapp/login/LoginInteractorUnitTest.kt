package com.rodolfogusson.bankapp.login

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.rodolfogusson.bankapp.login.domain.LoginData
import com.rodolfogusson.bankapp.login.domain.LoginDataValidator
import com.rodolfogusson.bankapp.login.domain.Validator
import com.rodolfogusson.bankapp.login.interactor.LoginInteractor
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LoginInteractorUnitTest {

    private lateinit var interactor: LoginInteractor

    @Before
    fun setup() {
        interactor = LoginInteractor()
    }

    @Test
    fun `when sendLoginRequest is called, login data is validated`() {
        //GIVEN
        val validatorSpy = spy<Validator>()
        val fakeLoginData = LoginData("","")
        interactor.validator = validatorSpy

        //WHEN
        interactor.sendLoginRequest(fakeLoginData)

        //THEN
        verify(validatorSpy, times(1)).validate(any())
    }

//    @Test
//    fun `when login data is valid, `

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
//            "When the valid input is passed to LoginInteractor "
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
