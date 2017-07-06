package com.ilaps.androidtest.common

import android.app.Fragment
import android.view.View
import kotlinx.android.synthetic.main.view_progress.*
import java.math.BigInteger
import java.security.SecureRandom

abstract class BaseFragment : Fragment(),BaseView {

    var fragmentId:String? = null

    init {
        generateId()
    }

    fun generateId(){
        var hash = ""
        this.javaClass.simpleName.map { hash = hash + BigInteger(1, SecureRandom()).toString() + it.toInt() + it}
        fragmentId = hash
    }
    override fun showLoading() {
        changeProgressVisibility(View.VISIBLE)
    }


    override fun hideLoading() {
        changeProgressVisibility(View.GONE)
    }

    fun changeProgressVisibility(visibility:Int){
        activity?.runOnUiThread({ progressLayout?.visibility = visibility })
    }
}