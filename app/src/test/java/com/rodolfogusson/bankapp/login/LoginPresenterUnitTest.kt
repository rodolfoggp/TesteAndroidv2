package com.rodolfogusson.bankapp.login

import com.rodolfogusson.bankapp.login.domain.LoginResponse
import com.rodolfogusson.bankapp.login.presentation.LoginPresenter
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class LoginPresenterUnitTest {

    @Test
    fun presentLoginData_with_validInput_shouldCall_displayLoginData() {
        // Given
        val presenter = LoginPresenter()
        val response = LoginResponse()
        // Set up the Spy or Mocks
        // response.listOfFlights = FlightWorker().futureFlights


        // When
        presenter.presentLoginData(response)

        // Then
        // Assert.assertTrue("When the isValid input is passed to presenter" +
        //        "Then displayLastSavedUser should be called",
        //         homeFragmentInputSpy.isDisplayHomeMetaDataCalled)
    }

    companion object {
        const val TAG = "HomePresenterUnitTest"
    }
}
