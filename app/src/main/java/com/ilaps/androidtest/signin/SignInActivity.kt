package com.ilaps.androidtest.signin

import android.os.Bundle
import com.ilaps.androidtest.R
import com.ilaps.androidtest.common.BaseActivity
import com.ilaps.androidtest.navigation.navigateToFragment

/**
 * Created by ricar on 4/7/17.
 */
class SignInActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        navigateToFragment(SignInFragment.newInstance(),R.id.signInContainer,false)
    }
}