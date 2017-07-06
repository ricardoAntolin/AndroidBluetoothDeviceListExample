package com.ilaps.androidtest.signin

import com.google.firebase.auth.FirebaseAuth
import com.ilaps.androidtest.common.auth.BaseAuthPresenter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignInPresenter @Inject constructor() : BaseAuthPresenter<SignInContract.View>(), SignInContract.Presenter {

    override fun signIn(email: String, pass: String) {
        auth.signInWithEmailAndPassword(email, pass)
                .addOnFailureListener { view?.showError(it) }
    }
}