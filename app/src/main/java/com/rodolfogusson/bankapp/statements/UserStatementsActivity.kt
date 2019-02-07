package com.rodolfogusson.bankapp.statements

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodolfogusson.bankapp.R

interface UserStatementsActivityInput {
    fun displayUserStatementsData(viewModel: UserStatementsViewModel)
}

class UserStatementsActivity : AppCompatActivity(), UserStatementsActivityInput {

    lateinit var output: UserStatementsInteractorInput
    lateinit var router: UserStatementsRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Do the setup
        setContentView(R.layout.activity_user_statements)

        UserStatementsConfigurator.configureActivity(this)
        fetchData()

        // Do other work
    }

    fun fetchData() {
        // create Request and set the needed input
        val request = UserStatementsRequest()

        // Call the output to fetch the data
        output.fetchUserStatementsData(request)
    }

    override fun displayUserStatementsData(viewModel: UserStatementsViewModel) {
        // Log.d(TAG, "displayUserStatementsData() called with: viewModel = [$viewModel]")
        // Deal with the data, update the states, ui etc..
    }

    companion object {
        const val TAG = "UserStatementsActivity"
    }
}
