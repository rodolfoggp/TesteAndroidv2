package com.rodolfogusson.bankapp.login

import com.nhaarman.mockitokotlin2.*
import kotlinx.android.synthetic.main.activity_login.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf


@RunWith(RobolectricTestRunner::class)
class LoginActivityUnitTest {

    private lateinit var activity: LoginActivity
    private lateinit var userData: User
    private val loginText = "teste@teste.com.br"
    private val passwordText = "a9f28j2S"

    @Before
    fun setup() {
        activity = Robolectric.setupActivity(LoginActivity::class.java)
        userData = User(loginText, passwordText)
        activity.user.setText(userData.login)
        activity.password.setText(userData.password)
    }

    @Test
    fun `activity is not null`(){
        assertNotNull(activity)
    }

    @Test
    fun `when button is clicked, user input is validated`() {
        //GIVEN
        val validatorSpy = spy<LoginDataValidator>()
        activity.validator = validatorSpy

        //WHEN
        activity.button.performClick()

        //THEN
        verify(validatorSpy, times(1)).validate(any())
    }

    //TODO: Break in two (after validation, do login; after login, call next activity)
    @Test
    fun `when user input is validated successfully, the next activity is called`() {
        //GIVEN
        val validatorMock = mock<Validator>()
        activity.validator = validatorMock
        val nextActivity = activity.router.determineNextScreen()

        //WHEN
        whenever(validatorMock.validate(any())).thenReturn(true)
        activity.button.performClick()

        //THEN
        val shadowActivity = shadowOf(activity)
        assertNotNull("Activity is null", shadowActivity)
        val intent = shadowActivity.nextStartedActivity
        assertNotNull("Intent is null", intent)
        val shadowIntent = shadowOf(intent)
        assertEquals(nextActivity::class.java.name, shadowIntent.intentClass.name)
    }


//    @Test
//    fun onButtonClicked_calls_sendUserInputToValidation_withCorrectData() {
//        //GIVEN
//        val activityOutputSpy = spy<LoginInteractor>()
//        activity.output = activityOutputSpy
//
//        //WHEN
//        activity.button.performClick()
//
//        //THEN
//        verify(activityOutputSpy, times(1)).sendUserInputToValidation(
//            argThat { login == loginText && password == passwordText })
//    }

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


//    private inner class LoginActivityOutputSpy : LoginInteractorInput {
//
//        var fetchLoginDataIsCalled = false
//        var validateDataIsCalled = false
//        lateinit var requestCopy: LoginRequest
//
//        override fun fetchLoginData(request: LoginRequest) {
//            fetchLoginDataIsCalled = true
//            requestCopy = request
//        }
//    }
}
