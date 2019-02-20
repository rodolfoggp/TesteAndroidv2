package com.rodolfogusson.bankapp.login

import com.nhaarman.mockitokotlin2.*
import com.rodolfogusson.bankapp.R
import com.rodolfogusson.bankapp.login.domain.LoginData
import com.rodolfogusson.bankapp.login.interactor.LoginInteractorInput
import com.rodolfogusson.bankapp.login.presentation.LoginActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.junit.After
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
    private val loginText = "teste@teste.com.br"
    private val passwordText = "a9f28j2S"
    private var interactorMock = mock<LoginInteractorInput>()

    @Before
    fun setUp() {
        activity = Robolectric.setupActivity(LoginActivity::class.java)
        activity.output = interactorMock
    }

    @Test
    fun `activity is not null`(){
        assertNotNull(activity)
    }

    @Test
    fun `when button is clicked, sendLoginRequest is called`() {
        //WHEN
        activity.loginButton.performClick()

        //THEN
        verify(interactorMock).sendLoginRequest(any())
    }

    @Test
    fun `button click calls sendLoginRequest with correct data`() {
        //GIVEN
        activity.userEditText.setText(loginText)
        activity.passwordEditText.setText(passwordText)

        //WHEN
        activity.loginButton.performClick()

        //THEN
        verify(interactorMock).sendLoginRequest(
            argThat { this.user == loginText && this.password == passwordText })
    }

    @Test
    fun `navigateToNextActivity should call the next activity`() {
        //GIVEN
        val nextActivityIntent = activity.router.determineNextScreen()

        //WHEN
        activity.navigateToNextActivity()

        //THEN
        val shadowActivity = shadowOf(activity)
        val triggeredIntent = shadowActivity.nextStartedActivity
        assertNotNull(triggeredIntent)
        val shadowIntent = shadowOf(triggeredIntent)
        assertEquals(nextActivityIntent.component.className, shadowIntent.intentClass.name)
    }

    @Test
    fun `onCreate calls fetchLastSavedUser`() {
        //WHEN
        activity.fetchUserData()

        //THEN
        verify(interactorMock).fetchLastSavedUser()
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
        activity.displayLoginError()

        //THEN
        val dialog = ShadowDialog.getLatestDialog()
        assertNotNull(dialog)
        assertTrue(dialog.isShowing)
    }
}
