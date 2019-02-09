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
    private lateinit var loginData: LoginData
    private val loginText = "teste@teste.com.br"
    private val passwordText = "a9f28j2S"

    @Before
    fun setup() {
        activity = Robolectric.setupActivity(LoginActivity::class.java)
        loginData = LoginData(loginText, passwordText)
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
        activity.user.setText(loginText)
        activity.password.setText(passwordText)

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

    @Test
    fun `when displayLastSavedUser is called, user field is correctly filled`() {
        //GIVEN
        val loginData = LoginData(loginText, passwordText)
        val viewModel = LoginViewModel(loginData)

        //WHEN
        activity.displayLastSavedUser(viewModel)

        //THEN
        assertEquals(activity.user.text.toString(), loginText)
    }

    @Test
    fun `when displayLastSavedUser is called, password field is correctly filled`() {
        //GIVEN
        val loginData = LoginData(loginText, passwordText)
        val viewModel = LoginViewModel(loginData)

        //WHEN
        activity.displayLastSavedUser(viewModel)

        //THEN
        assertEquals(activity.password.text.toString(), passwordText)
    }
}
