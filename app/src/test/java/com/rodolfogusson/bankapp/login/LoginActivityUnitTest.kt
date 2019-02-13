package com.rodolfogusson.bankapp.login

import android.widget.TextView
import com.nhaarman.mockitokotlin2.*
import com.rodolfogusson.bankapp.R
import com.rodolfogusson.bankapp.login.domain.LoginData
import com.rodolfogusson.bankapp.login.interactor.LoginInteractorInput
import com.rodolfogusson.bankapp.login.presentation.LoginActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.shadows.ShadowDialog


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
    fun `when button is clicked, sendLoginRequest is called`() {
        //GIVEN
        val interactorSpy = spy<LoginInteractorInput>()
        activity.output = interactorSpy

        //WHEN
        activity.loginButton.performClick()

        //THEN
        verify(interactorSpy).sendLoginRequest(any())
    }

    @Test
    fun `button click calls sendLoginRequest with correct data`() {
        //GIVEN
        val interactorSpy = spy<LoginInteractorInput>()
        activity.output = interactorSpy
        activity.userEditText.setText(loginText)
        activity.passwordEditText.setText(passwordText)

        //WHEN
        activity.loginButton.performClick()

        //THEN
        verify(interactorSpy).sendLoginRequest(
            argThat { this.login == loginText && this.password == passwordText })
    }

    @Test
    fun `navigateToNextActivity should call the next activity`() {
        //GIVEN
        val nextActivity = activity.router.determineNextScreen()

        //WHEN
        activity.navigateToNextActivity()

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
        verify(interactorSpy).fetchLastSavedUser()
    }

    @Test
    fun `when displayLastSavedUser is called, user field is correctly filled`() {
        //GIVEN
        val loginData = LoginData(loginText, passwordText)

        //WHEN
        activity.displayLastSavedUser(loginData)

        //THEN
        assertEquals(activity.userEditText.text.toString(), loginText)
    }

    @Test
    fun `when displayLastSavedUser is called, password field is correctly filled`() {
        //GIVEN
        val loginData = LoginData(loginText, passwordText)

        //WHEN
        activity.displayLastSavedUser(loginData)

        //THEN
        assertEquals(activity.passwordEditText.text.toString(), passwordText)
    }

    @Test
    fun `displayUserError should set the user error view text`() {
        //GIVEN
        val stringId = R.string.invalid_user_error

        //WHEN
        activity.displayUserError(stringId)

        //THEN
        assertEquals(activity.getString(R.string.invalid_user_error), activity.userError.text.toString())
    }

    @Test
    fun `displayPasswordError should set the password error view text`() {
        //GIVEN
        val stringId = R.string.invalid_password_error

        //WHEN
        activity.displayPasswordError(stringId)

        //THEN
        assertEquals(activity.getString(R.string.invalid_password_error), activity.passwordError.text.toString())
    }

    @Test
    fun `when loginButton is clicked, errors should be cleared`() {
        //GIVEN
        activity.userError.setText(R.string.invalid_user_error)
        activity.passwordError.setText(R.string.invalid_password_error)
        val interactorMock = mock<LoginInteractorInput>()
        activity.output = interactorMock

        //WHEN
        activity.loginButton.performClick()

        //THEN
        assertTrue(activity.userError.text.toString().isEmpty())
        assertTrue(activity.passwordError.text.toString().isEmpty())
    }

    @Test
    fun `displayLoginError should show an AlertDialog`() {
        //GIVEN
        val initialDialog = ShadowDialog.getLatestDialog()
        assertNull(initialDialog)

        //WHEN
        activity.displayLoginError(R.string.error_dialog_title, R.string.login_failed)

        //THEN
        val dialog = ShadowDialog.getLatestDialog()
        assertNotNull(dialog)
        assertTrue(dialog.isShowing)
    }
}
