package com.ilaps.androidtest.common.auth

import com.google.firebase.auth.FirebaseAuth

/**
 * Created by ricar on 5/7/17.
 */
abstract class BaseAuthPresenter<T : BaseAuthView > : FirebaseAuth.AuthStateListener {
    var view: T? = null

    var auth = FirebaseAuth.getInstance()!!

    fun onStart(view: T) {
        this.view = view
        auth.addAuthStateListener(this)
    }

    fun onStop() {
        auth.removeAuthStateListener(this)
    }

    override fun onAuthStateChanged(p0: FirebaseAuth) {
        val user = p0.currentUser
        if (user != null) view?.userLogged(user)
    }
}