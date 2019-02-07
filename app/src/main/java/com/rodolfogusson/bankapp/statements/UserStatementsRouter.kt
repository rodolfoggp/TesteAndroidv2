package com.rodolfogusson.bankapp.statements

import android.content.Intent
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity

import java.lang.ref.WeakReference

interface UserStatementsRouterInput {
    fun determineNextScreen(position: Int): Activity
    fun passDataToNextScene(position: Int, nextActivity: Activity)
}

class UserStatementsRouter : UserStatementsRouterInput, AdapterView.OnItemClickListener {

    var activity: WeakReference<UserStatementsActivity>? = null

    override fun determineNextScreen(position: Int): Activity {
        // Based on the position or some other data decide what is the next scene
        // return if (someCondition()) {
        //     OneActivity()
        // } else {
        //     TwoActivity()
        // }
        return Activity()
    }

    override fun passDataToNextScene(position: Int, nextActivity: Activity) {
        // Based on the position or some other data decide the data for the next scene

        // val args =  Bundle()
        // args.putParcelable("flight",flight)
        // nextActivity.arguments = args
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        // Log.d(TAG, "onItemClick() called with: parent = [$parent], "
        // + "view = [$view], position = [$position], id = [$id]")
        val nextActivity = determineNextScreen(position)
        passDataToNextScene(position, nextActivity)
        // Ask the activity to show the next activity. eg ..
        // activity?.get()?.homeActivityListener?.startPastTripActivity(nextActivity)

    }

    companion object {
        const val TAG = "HomeRouter"
    }
}
