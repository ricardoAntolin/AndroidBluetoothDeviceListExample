package com.ilaps.androidtest.common.auth

import com.google.firebase.auth.FirebaseUser
import com.ilaps.androidtest.common.BaseView

/**
 * Created by ricar on 5/7/17.
 */
interface BaseAuthView: BaseView {
    fun userLogged(user: FirebaseUser)
}