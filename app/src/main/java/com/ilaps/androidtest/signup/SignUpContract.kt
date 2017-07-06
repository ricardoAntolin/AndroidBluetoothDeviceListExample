package com.ilaps.androidtest.signup

import com.ilaps.androidtest.common.BasePresenter
import com.ilaps.androidtest.common.auth.BaseAuthView

/**
 * Created by ricar on 4/7/17.
 */
interface SignUpContract {
    interface View: BaseAuthView

    interface Presenter: BasePresenter {
        fun signUp(email:String, pass:String)
    }
}