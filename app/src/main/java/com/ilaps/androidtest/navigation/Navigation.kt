package com.ilaps.androidtest.navigation

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import com.ilaps.androidtest.main.BluetoothDeviceListActivity
import com.ilaps.androidtest.signin.SignInActivity
import com.ilaps.androidtest.signup.SignUpActivity
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ricar on 5/7/17.
 */
@Singleton
class Navigation @Inject constructor() {
    fun navigateToSignUp(originActivity: Activity){
        originActivity.navigateToActivity(SignUpActivity::class.java)
    }

    fun navigateToSignIn(originActivity: Activity){
        originActivity.navigateToActivityRemovingPrevious(SignInActivity::class.java)
    }

    fun navigateToBluetoothDeviceListActivity(originActivity: Activity){
        originActivity.navigateToActivityRemovingPrevious(BluetoothDeviceListActivity::class.java)
    }
}