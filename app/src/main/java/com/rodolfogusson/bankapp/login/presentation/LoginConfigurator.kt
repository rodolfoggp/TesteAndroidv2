package com.rodolfogusson.bankapp.login.presentation

import com.rodolfogusson.bankapp.core.network.ServiceProvider
import com.rodolfogusson.bankapp.login.data.LoginRepository
import com.rodolfogusson.bankapp.login.domain.LoginDataValidator
import com.rodolfogusson.bankapp.login.interactor.LoginInteractor
import java.lang.ref.WeakReference

object LoginConfigurator {

    fun configureActivity(activity: LoginActivity) {

        val router = LoginRouter(WeakReference(activity))

        val presenter = LoginPresenter(WeakReference(activity))

        val bankService = ServiceProvider.bankService()
        val loginRepository = LoginRepository
        val interactor = LoginInteractor(presenter, LoginDataValidator(), loginRepository)

        activity.output = interactor
        activity.router = router
    }
}
