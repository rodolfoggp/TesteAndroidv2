package com.rodolfogusson.bankapp.login

import com.rodolfogusson.bankapp.login.presentation.LoginActivity
import com.rodolfogusson.bankapp.login.presentation.LoginRouter
import com.rodolfogusson.bankapp.statements.UserStatementsActivity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import java.lang.ref.WeakReference

@RunWith(RobolectricTestRunner::class)
class LoginRouterUnitTest {

    private lateinit var router: LoginRouter
    private lateinit var activity: LoginActivity

    @Before
    fun setup() {
        router = LoginRouter()
        activity = Robolectric.setupActivity(LoginActivity::class.java)
        router.activity = WeakReference(activity)
    }

    @Test
    fun `determineNextScreen should return UserStatementsActivity`() {
        //WHEN
        val intent = router.determineNextScreen()

        //THEN
        assertNotNull(intent)
        val shadowIntent = Shadows.shadowOf(intent)
        assertEquals(UserStatementsActivity::class.java.name, shadowIntent.intentClass.name)
    }

    companion object {
        const val TAG = "LoginRouterUnitTest"
    }
}
