package com.rodolfogusson.bankapp

import java.lang.ref.WeakReference

object LoginConfigurator {

    fun configureActivity(activity: LoginActivity) {

        val router = LoginRouter()
        router.fragment = WeakReference(activity)

        val presenter = LoginPresenter()
        presenter.output = WeakReference(activity)

        val interactor = LoginInteractor()
        interactor.output = presenter

        activity.output = interactor
        activity.router = router
    }
}
