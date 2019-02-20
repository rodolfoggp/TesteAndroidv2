package com.rodolfogusson.bankapp.login.data

import android.content.Context
import android.preference.PreferenceManager
import androidx.test.core.app.ApplicationProvider
import com.rodolfogusson.bankapp.login.domain.LoginData
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CredentialManagerTest {

    lateinit var credentialManager: CredentialManager
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    private val loginData = LoginData("user", "password")

    @Before
    fun setUp() {
        credentialManager = CredentialManager(sharedPreferences)
    }

    @Test
    fun `saveUserCredentials saves correct data on sharedPreferences`() {
        //WHEN
        credentialManager.saveUserCredentials(loginData)
        val data = credentialManager.getUserCredentials()

        //THEN
        assertEquals(data?.user, loginData.user)
        assertEquals(data?.password, loginData.password)
    }
}