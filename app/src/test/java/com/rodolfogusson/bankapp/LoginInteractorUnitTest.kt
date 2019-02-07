package com.rodolfogusson.bankapp

import com.rodolfogusson.bankapp.login.LoginInteractor
import com.rodolfogusson.bankapp.login.LoginPresenterInput
import com.rodolfogusson.bankapp.login.LoginRequest
import com.rodolfogusson.bankapp.login.LoginResponse
import org.junit.Assert
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
    fun whenReceivingUserInput_validateLoginData_isCalled() {
        //GIVEN
        //WHEN

    }

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
