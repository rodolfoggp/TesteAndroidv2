package com.rodolfogusson.bankapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.rodolfogusson.bankapp.login.presentation.LoginActivity
import com.rodolfogusson.bankapp.statements.UserStatementsActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class LoginActivityInstrumentedTest {

    @get:Rule
    var activityRule: ActivityTestRule<LoginActivity>
            = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.rodolfogusson.bankapp", appContext.packageName)
    }

    @Test
    fun userEditTextIsShown() {
        onView(withId(R.id.userEditText)).check(matches(isDisplayed()))
    }

    @Test
    fun passwordEditTextIsShown() {
        onView(withId(R.id.passwordEditText)).check(matches(isDisplayed()))
    }

    @Test
    fun whenUserEntersLoginData_andClicksLoginButton_UserStatementsActivityIsShown() {
        //A user with these credentials wants to login
        val loginText = "teste@teste.com.br"
        val passwordText = "a9f28j2S"

        //The user types in its login data in each field
        onView(withId(R.id.userEditText)).perform(typeText(loginText))
        onView(withId(R.id.passwordEditText)).perform(typeText(passwordText), closeSoftKeyboard())

        //And clicks the login button
        Intents.init()
        val loginRequest = activityRule.activity.output.idlingResource
        IdlingRegistry.getInstance().register(loginRequest)
        onView(withId(R.id.loginButton)).perform(click())

        //The application logs the user in, then the UserStatementsActivity is shown
        intended(hasComponent(UserStatementsActivity::class.java.name))
    }
}
