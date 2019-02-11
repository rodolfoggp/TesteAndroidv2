package com.rodolfogusson.bankapp.login.presentation

import androidx.appcompat.app.AppCompatActivity

import java.lang.ref.WeakReference

interface LoginRouterInput {
    fun determineNextScreen(): AppCompatActivity
    fun passDataToNextScene(position: Int, nextFragment: AppCompatActivity)
}

class LoginRouter : LoginRouterInput {

    var activity: WeakReference<LoginActivity>? = null

    override fun determineNextScreen(): AppCompatActivity {
        // Based on the position or some other data decide what is the next scene
        // return if (someCondition()) {
        //     OneFragment()
        // } else {
        //     TwoFragment()
        // }
        return AppCompatActivity()
    }

    override fun passDataToNextScene(position: Int, nextFragment: AppCompatActivity) {
        // Based on the position or some other data decide the data for the next scene

        // val args =  Bundle()
        // args.putParcelable("flight",flight)
        // nextFragment.arguments = args
    }

//    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
//        // Log.d(TAG, "onItemClick() called with: parent = [$parent], "
//        // + "view = [$view], position = [$position], id = [$id]")
//        val nextFragment = determineNextScreen(position)
//        passDataToNextScene(position, nextFragment)
//        // Ask the activity to show the next activity. eg ..
//        // activity?.get()?.homeFragmentListener?.startPastTripFragment(nextFragment)
//
//    }

    companion object {
        const val TAG = "HomeRouter"
    }
}
