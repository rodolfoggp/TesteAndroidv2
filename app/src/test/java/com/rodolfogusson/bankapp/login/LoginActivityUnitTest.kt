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
    fun `when button is clicked, validation is called`() {
        //GIVEN
        val validatorSpy = spy<Validator>()
        activity.validator = validatorSpy

        //WHEN
        activity.button.performClick()

        //THEN
        verify(validatorSpy, times(1)).validate(any())
    }

    @Test
    fun `when button is clicked and validation is successful, sendLoginRequest is called`() {
        //GIVEN
        val validatorMock = mock<Validator>()
        val interactorSpy = spy<LoginInteractorInput>()
        activity.validator = validatorMock
        activity.output = interactorSpy

        //WHEN
        whenever(validatorMock.validate(any())).thenReturn(true)
        activity.button.performClick()

        //THEN
        verify(interactorSpy, times(1)).sendLoginRequest(any())
    }

    @Test
    fun `button click calls sendLoginRequest with correct data`() {
        //GIVEN
        val validatorMock = mock<Validator>()
        val interactorSpy = spy<LoginInteractorInput>()
        activity.validator = validatorMock
        activity.output = interactorSpy

        //WHEN
        whenever(validatorMock.validate(any())).thenReturn(true)
        activity.button.performClick()

        //THEN
        verify(interactorSpy).sendLoginRequest(
            argThat { this.login == loginText && this.password == passwordText })
    }

    @Test
    fun `when login was successful, the next activity is called`() {
        //GIVEN
        val nextActivity = activity.router.determineNextScreen()

        //WHEN
        activity.onLoginSuccessful()

        //THEN
        val shadowActivity = shadowOf(activity)
        val intent = shadowActivity.nextStartedActivity
        assertNotNull(intent)
        val shadowIntent = shadowOf(intent)
        assertEquals(nextActivity::class.java.name, shadowIntent.intentClass.name)
    }

    @Test
    fun `onCreate calls fetchLastSavedUser`() {
        //GIVEN
        val interactorSpy = spy<LoginInteractorInput>()
        activity.output = interactorSpy

        //WHEN
        activity.fetchUserData()

        //THEN
        verify(interactorSpy, times(1)).fetchLastSavedUser()
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
