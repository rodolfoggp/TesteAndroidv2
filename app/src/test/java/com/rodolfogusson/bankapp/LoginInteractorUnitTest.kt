package com.rodolfogusson.bankapp

import android.util.Log
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LoginInteractorUnitTest {

    @Test
    fun fetchLoginData_with_validInput_shouldCall_presentLoginData() {
        // Given
        val interactor = LoginInteractor()
        val request = LoginRequest()
        //homeRequest.isFutureTrips = true
        val presenterInputSpy = LoginPresenterInputSpy()
        interactor.output = presenterInputSpy
        // When
        interactor.fetchLoginData(request)

        // Then
        Assert.assertTrue(
            "When the valid input is passed to LoginInteractor "
                    + "Then presentLoginData should be called",
            presenterInputSpy.presentLoginDataIsCalled
        )
    }

    private inner class LoginPresenterInputSpy : LoginPresenterInput {

        internal var presentLoginDataIsCalled = false
        internal var responseCopy: LoginResponse? = null
        override fun presentLoginData(response: LoginResponse) {
            presentLoginDataIsCalled = true
            responseCopy = response
        }
    }


}
