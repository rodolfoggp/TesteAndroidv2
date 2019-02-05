package com.rodolfogusson.bankapp

import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.android.synthetic.main.activity_login.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class LoginActivityUnitTest {

    private lateinit var activity: LoginActivity

    @Before
    fun setup() {
        activity = Robolectric.setupActivity(LoginActivity::class.java)
    }

    @Test
    fun onButtonClicked_shouldCall_validateData() {
        //GIVEN
        val activityOutputSpy = spy<LoginInteractor>()
        val button = activity.button

        //WHEN
        //activity.buttonClicked(button)
        button.performClick()

        //THEN
        verify(activityOutputSpy, times(1)).sendUserInputToValidation()
    }

//    @Test
//    fun field_User_Validates_Email() {
//        //Given
//        val etUsername = activity.etUsername
//        val emailText = "test@test.com"
//
//        //When
//        etUsername.text = emailText
//        activity.buttonClicked()
//
//        //Then
//        Assert.assertTrue()
//    }

//    @Test
//    fun onCreate_shouldCall_fetchLoginData() {
//        // Given
//        val activityOutputSpy = LoginActivityOutputSpy()
//
//        // It must have called the onCreate earlier,
//        // we are injecting the mock and calling the fetchData to test our condition
//        val activity = LoginActivity()
//        activity.output = activityOutputSpy
//
//        // When
//        activity.fetchData()
//
//        // Then
//        Assert.assertTrue(activityOutputSpy.fetchLoginDataIsCalled)
//    }
//
//    @Test
//    fun onCreate_Calls_fetchLoginData_withCorrectData() {
//        // Given
//        val activityOutputSpy = LoginActivityOutputSpy()
//        val activity = LoginActivity()
//        activity.output = activityOutputSpy
//
//        // When
//        activity.fetchData()
//
//        // Then
//        Assert.assertNotNull(LoginActivity)
//        // Assert.assertTrue(activityOutputSpy.requestCopy.isFutureTrips)
//    }


    private inner class LoginActivityOutputSpy : LoginInteractorInput {

        var fetchLoginDataIsCalled = false
        var validateDataIsCalled = false
        lateinit var requestCopy: LoginRequest

        override fun fetchLoginData(request: LoginRequest) {
            fetchLoginDataIsCalled = true
            requestCopy = request
        }
    }
}
