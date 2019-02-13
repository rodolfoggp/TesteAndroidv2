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
import org.robolectric.RobolectricTestRunner
import java.lang.ref.WeakReference


@RunWith(RobolectricTestRunner::class)
class LoginPresenterUnitTest {

    private lateinit var presenter: LoginPresenter
    private var loginActivityMock = mock<LoginActivityInput>()
    private var output = WeakReference<LoginActivityInput>(loginActivityMock)
    private var loginDataMock = mock<LoginData>()
    private var userMock = mock<User>{
        on { loginData } doReturn loginDataMock
    }

    @Before
    fun setup() {
        presenter = LoginPresenter()
    }

    @Test
    fun `when onSavedUserFetched is called, the fetched user should be displayed in the activity`() {
        //GIVEN
        presenter.output = output

        //WHEN
        presenter.onSavedUserFetched(userMock)

        //THEN
        verify(loginActivityMock).displayLastSavedUser(any())
    }

    @Test
    fun `when onSavedUserFetched is called, activity should receive the correct view model`() {
        //GIVEN
        presenter.output = output

        //WHEN
        presenter.onSavedUserFetched(userMock)

        //THEN
        verify(loginActivityMock).displayLastSavedUser(argThat { this == userMock.loginData })
    }

    @Test
    fun `when receiving a InvalidEmailOrCPF error, displayUserError should be called`(){
        //GIVEN
        val validation = Validation(false, arrayListOf(InvalidEmailOrCPF))
        presenter.output = output

        //WHEN
        presenter.presentValidationErrors(validation)

        //THEN
        verify(loginActivityMock).displayUserError(any())
    }

    @Test
    fun `when receiving an InvalidEmailOrCPF error, displayUserError should receive user error id`(){
        //GIVEN
        val validation = Validation(false, arrayListOf(InvalidEmailOrCPF))
        presenter.output = output

        //WHEN
        presenter.presentValidationErrors(validation)

        //THEN
        verify(loginActivityMock).displayUserError(R.string.invalid_user_error)
    }

    @Test
    fun `when receiving an InvalidPassword error, displayPasswordError should be called`(){
        //GIVEN
        val validation = Validation(false, arrayListOf(InvalidPassword))
        presenter.output = output

        //WHEN
        presenter.presentValidationErrors(validation)

        //THEN
        verify(loginActivityMock).displayPasswordError(any())
    }

    @Test
    fun `when receiving an InvalidPassword error, displayPasswordError should receive password error id`(){
        //GIVEN
        val validation = Validation(false, arrayListOf(InvalidPassword))
        presenter.output = output

        //WHEN
        presenter.presentValidationErrors(validation)

        //THEN
        verify(loginActivityMock).displayPasswordError(R.string.invalid_password_error)
    }

//    @Test
//    fun presentLoginData_with_validInput_shouldCall_displayLoginData() {
//        // Given
//        //val response = LoginResponse()
//        // Set up the Spy or Mocks
//        // response.listOfFlights = FlightWorker().futureFlights
//
//
//        // When
//        //presenter.presentLoginData(response)
//
//        // Then
//        // Assert.assertTrue("When the isValid input is passed to presenter" +
//        //        "Then displayLastSavedUser should be called",
//        //         homeFragmentInputSpy.isDisplayHomeMetaDataCalled)
//    }

    companion object {
        const val TAG = "HomePresenterUnitTest"
    }
}
