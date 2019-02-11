package com.rodolfogusson.bankapp.login.presentation

import com.rodolfogusson.bankapp.login.domain.LoginDataValidator
import com.rodolfogusson.bankapp.login.interactor.LoginInteractor
import java.lang.ref.WeakReference

object LoginConfigurator {

    fun configureActivity(activity: LoginActivity) {

        val router = LoginRouter()
        router.activity = WeakReference(activity)

        val presenter = LoginPresenter()
        presenter.output = WeakReference(activity)

        val interactor = LoginInteractor()
        interactor.output = presenter

        activity.output = interactor
        activity.router = router
    }
}
