package com.ilaps.androidtest.signin

import com.ilaps.androidtest.common.BasePresenter
import com.ilaps.androidtest.common.auth.BaseAuthView

/**
 * Created by ricar on 4/7/17.
 */
interface SignInContract {
    interface View: BaseAuthView {
        fun userLoginIncorrect()
    }

    interface Presenter: BasePresenter {
        fun signIn(email:String, pass:String)
    }
}