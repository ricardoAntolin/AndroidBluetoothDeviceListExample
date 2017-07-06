package com.ilaps.androidtest.signup

import com.ilaps.androidtest.common.auth.BaseAuthPresenter
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ricar on 4/7/17.
 */
@Singleton
class SignUpPresenter @Inject constructor(): BaseAuthPresenter<SignUpContract.View>(), SignUpContract.Presenter {
    override fun signUp(email: String, pass: String) {
        auth.createUserWithEmailAndPassword(email, pass)
                .addOnFailureListener { view?.showError(it) }
    }
}