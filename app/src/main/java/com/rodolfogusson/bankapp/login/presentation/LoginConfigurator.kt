package com.rodolfogusson.bankapp.login.presentation

import android.content.Context.MODE_PRIVATE
import com.rodolfogusson.bankapp.core.network.ServiceProvider
import com.rodolfogusson.bankapp.login.data.CredentialManager
import com.rodolfogusson.bankapp.login.data.LoginRepository
import com.rodolfogusson.bankapp.login.domain.LoginDataValidator
import com.rodolfogusson.bankapp.login.interactor.LoginInteractor
import java.lang.ref.WeakReference

object LoginConfigurator {

    fun configureActivity(activity: LoginActivity) {

        val router = LoginRouter(WeakReference(activity))

        val presenter = LoginPresenter(WeakReference(activity))

        val loginRepository = LoginRepository
        loginRepository.bankService = ServiceProvider.bankService()
        loginRepository.credentialManager =
            CredentialManager(activity.getSharedPreferences(CredentialManager.appPreferences, MODE_PRIVATE))
        val interactor = LoginInteractor(presenter, LoginDataValidator(), loginRepository)

        activity.output = interactor
        activity.router = router
    }
}
