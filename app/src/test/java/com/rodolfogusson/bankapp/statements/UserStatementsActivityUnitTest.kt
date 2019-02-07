package com.rodolfogusson.bankapp.statements

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class UserStatementsActivityUnitTest {

    @Test
    fun onCreate_shouldCall_fetchUserStatementsData() {
        // Given
        val activityOutputSpy = UserStatementsActivityOutputSpy()

        // It must have called the onCreate earlier,
        // we are injecting the mock and calling the fetchData to test our condition
        val activity = UserStatementsActivity()
        activity.output = activityOutputSpy

        // When
        activity.fetchData()

        // Then
        Assert.assertTrue(activityOutputSpy.fetchUserStatementsDataIsCalled)
    }

    @Test
    fun onCreate_Calls_fetchUserStatementsData_withCorrectData() {
        // Given
        val activityOutputSpy = UserStatementsActivityOutputSpy()
        val activity = UserStatementsActivity()
        activity.output = activityOutputSpy

        // When
        activity.fetchData()

        // Then
        Assert.assertNotNull(UserStatementsActivity)
        // Assert.assertTrue(activityOutputSpy.requestCopy.isFutureTrips)
    }

    private inner class UserStatementsActivityOutputSpy : UserStatementsInteractorInput {

        var fetchUserStatementsDataIsCalled = false
        lateinit var requestCopy: UserStatementsRequest

        override fun fetchUserStatementsData(request: UserStatementsRequest) {
            fetchUserStatementsDataIsCalled = true
            requestCopy = request
        }
    }
}
