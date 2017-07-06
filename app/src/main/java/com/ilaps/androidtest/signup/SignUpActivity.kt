package com.ilaps.androidtest.signup

import android.os.Bundle
import com.ilaps.androidtest.R
import com.ilaps.androidtest.common.BaseActivity
import com.ilaps.androidtest.navigation.navigateToFragment

/**
 * Created by ricar on 4/7/17.
 */
class SignUpActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        navigateToFragment(SignUpFragment.newInstance(),R.id.signUpContainer,false)
    }
}