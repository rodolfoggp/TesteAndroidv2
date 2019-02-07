package com.rodolfogusson.bankapp.statements

import java.lang.ref.WeakReference

object UserStatementsConfigurator {

    fun configureActivity(activity: UserStatementsActivity) {

        val router = UserStatementsRouter()
        router.activity = WeakReference(activity)

        val presenter = UserStatementsPresenter()
        presenter.output = WeakReference(activity)

        val interactor = UserStatementsInteractor()
        interactor.output = presenter

        activity.output = interactor
        activity.router = router
    }
}
