package com.rodolfogusson.bankapp.statements

import android.util.Log
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.lang.ref.WeakReference

@RunWith(RobolectricTestRunner::class)
class UserStatementsRouterUnitTest {

    @Test
    fun test_UserStatementsRouter_determineNextScreen_when_Input_Is() {
        // Given
        // Setup Data

        val router = UserStatementsRouter()
        val activity = UserStatementsActivity()
        activity.router = router
        router.activity = WeakReference(activity)

        // When
        // Based on the position or some other data decide what is the next scene

        val nextActivity = router.determineNextScreen(0)

        // Then

        val nextActivityName = nextActivity.javaClass.name
        // Assert.assertEquals("When the some Data passed to UserStatementsRouter" +
        //        " Then next Activity should be ...",
        //        nextActivityName,
        //        SomeActivity::class.java.name)
    }

    companion object {
        const val TAG = "UserStatementsRouterUnitTest"
    }
}
