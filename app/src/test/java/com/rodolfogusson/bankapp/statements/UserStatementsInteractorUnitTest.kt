package com.rodolfogusson.bankapp.statements

import android.util.Log
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class UserStatementsInteractorUnitTest {

    @Test
    fun fetchUserStatementsData_with_validInput_shouldCall_presentUserStatementsData() {
        // Given
        val interactor = UserStatementsInteractor()
        val request = UserStatementsRequest()
        //homeRequest.isFutureTrips = true
        val presenterInputSpy = UserStatementsPresenterInputSpy()
        interactor.output = presenterInputSpy
        // When
        interactor.fetchUserStatementsData(request)

        // Then
        Assert.assertTrue(
            "When the isValid input is passed to UserStatementsInteractor "
                    + "Then presentUserStatementsData should be called",
            presenterInputSpy.presentUserStatementsDataIsCalled
        )
    }

    private inner class UserStatementsPresenterInputSpy : UserStatementsPresenterInput {

        internal var presentUserStatementsDataIsCalled = false
        internal var responseCopy: UserStatementsResponse? = null
        override fun presentUserStatementsData(response: UserStatementsResponse) {
            presentUserStatementsDataIsCalled = true
            responseCopy = response
        }
    }


}
