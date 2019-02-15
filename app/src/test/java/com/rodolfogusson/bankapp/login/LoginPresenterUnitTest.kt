package com.rodolfogusson.bankapp.login

import com.nhaarman.mockitokotlin2.*
import com.rodolfogusson.bankapp.R
import com.rodolfogusson.bankapp.login.domain.LoginData
import com.rodolfogusson.bankapp.login.domain.User
import com.rodolfogusson.bankapp.login.domain.Validation
import com.rodolfogusson.bankapp.login.domain.Validation.ValidationError.*
import com.rodolfogusson.bankapp.login.presentation.LoginActivityInput
import com.rodolfogusson.bankapp.login.presentation.LoginPresenter
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.intThat
import org.robolectric.RobolectricTestRunner
import java.lang.ref.WeakReference


@RunWith(RobolectricTestRunner::class)
class LoginPresenterUnitTest {

    private lateinit var presenter: LoginPresenter
    private val loginActivityMock = mock<LoginActivityInput>()
    private val output = WeakReference<LoginActivityInput>(loginActivityMock)
    private val loginDataMock = mock<LoginData>()
    private val userMock = mock<User>{
        on { loginData } doReturn loginDataMock
    }

    @Before
    fun setup() {
        presenter = LoginPresenter(output)
    }

    @Test
    fun `when onSavedUserFetched is called, the fetched user should be displayed in the activity`() {
        //WHEN
        presenter.onSavedUserFetched(userMock)

        //THEN
        verify(loginActivityMock).displayLastSavedUser(any())
    }

    @Test
    fun `when onSavedUserFetched is called, activity should receive the correct view model`() {
        //WHEN
        presenter.onSavedUserFetched(userMock)

        //THEN
        verify(loginActivityMock).displayLastSavedUser(argThat { this == userMock.loginData })
    }

    @Test
    fun `when receiving a InvalidEmailOrCPF error, displayUserError should be called`(){
        //GIVEN
        val validation = Validation(false, arrayListOf(InvalidEmailOrCPF))

        //WHEN
        presenter.presentValidationErrors(validation)

        //THEN
        verify(loginActivityMock).displayUserError(any())
    }

    @Test
    fun `when receiving an InvalidEmailOrCPF error, displayUserError should receive user error id`(){
        //GIVEN
        val validation = Validation(false, arrayListOf(InvalidEmailOrCPF))

        //WHEN
        presenter.presentValidationErrors(validation)

        //THEN
        verify(loginActivityMock).displayUserError(R.string.invalid_user_error)
    }

    @Test
    fun `when receiving an InvalidPassword error, displayPasswordError should be called`(){
        //GIVEN
        val validation = Validation(false, arrayListOf(InvalidPassword))

        //WHEN
        presenter.presentValidationErrors(validation)

        //THEN
        verify(loginActivityMock).displayPasswordError(any())
    }

    @Test
    fun `when receiving an InvalidPassword error, displayPasswordError should receive password error id`(){
        //GIVEN
        val validation = Validation(false, arrayListOf(InvalidPassword))

        //WHEN
        presenter.presentValidationErrors(validation)

        //THEN
        verify(loginActivityMock).displayPasswordError(R.string.invalid_password_error)
    }

    @Test
    fun `onLoginSuccessful should call navigateToNextActivity`() {
        //WHEN
        presenter.onLoginSuccessful()

        //THEN
        verify(loginActivityMock).navigateToNextActivity()
    }

    @Test
    fun `onLoginFailed should call displayLoginError`() {
        //GIVEN
        val errorMock = mock<Throwable>()

        //WHEN
        presenter.onLoginFailed(errorMock)

        //THEN
        verify(loginActivityMock).displayLoginError(any(), any())
    }

    @Test
    fun `presenter calls displayLoginError with the correct input`() {
        //GIVEN
        val errorMock = mock<Throwable>()

        //WHEN
        presenter.onLoginFailed(errorMock)

        //THEN
        verify(loginActivityMock).displayLoginError(
            intThat { it == R.string.error_dialog_title},
            intThat { it == R.string.login_failed })
    }

    companion object {
        const val TAG = "HomePresenterUnitTest"
    }
}
