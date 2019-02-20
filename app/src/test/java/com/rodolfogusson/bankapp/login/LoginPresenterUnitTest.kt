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
    fun setUp() {
        presenter = LoginPresenter(output)
    }

    @Test
    fun `when onSavedUserFetched is called, the fetched user should be displayed in the activity`() {
        //WHEN
        presenter.onSavedUserFetched(loginDataMock)

        //THEN
        verify(loginActivityMock).displayLastSavedUser(any())
    }

    @Test
    fun `when onSavedUserFetched is called, activity should receive the correct data`() {
        //WHEN
        presenter.onSavedUserFetched(loginDataMock)

        //THEN
        verify(loginActivityMock).displayLastSavedUser(argThat { this == loginDataMock })
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
        //WHEN
        presenter.onLoginFailed()

        //THEN
        verify(loginActivityMock).displayLoginError()
    }

    @Test
    fun `presenter calls displayLoginError with the correct input`() {
        //GIVEN
        val errorMessageMock = "Error mock"

        //WHEN
        presenter.onLoginFailed(errorMessageMock)

        //THEN
        verify(loginActivityMock).displayLoginError(argThat { this == errorMessageMock})
    }

    companion object {
        const val TAG = "HomePresenterUnitTest"
    }
}
