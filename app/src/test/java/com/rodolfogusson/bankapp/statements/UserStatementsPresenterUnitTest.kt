package com.rodolfogusson.bankapp.statements

import android.util.Log
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class UserStatementsPresenterUnitTest {

    @Test
    fun presentUserStatementsData_with_validInput_shouldCall_displayUserStatementsData() {
        // Given
        val presenter = UserStatementsPresenter()
        val response = UserStatementsResponse()
        // Set up the Spy or Mocks
        // response.listOfFlights = FlightWorker().futureFlights


        // When
        presenter.presentUserStatementsData(response)

        // Then
        // Assert.assertTrue("When the valid input is passed to presenter" +
        //        "Then displayUserStatementsData should be called",
        //         homeActivityInputSpy.isDisplayHomeMetaDataCalled)
    }

    companion object {
        const val TAG = "HomePresenterUnitTest"
    }
}
