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

        val validator = LoginDataValidator()
        val interactor = LoginInteractor()
        interactor.output = presenter
        interactor.validator = validator

        activity.output = interactor
        activity.router = router
    }
}
