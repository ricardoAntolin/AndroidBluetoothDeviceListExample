package com.ilaps.androidtest.common

/**
 * Created by ricar on 4/7/17.
 */
interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun showError(exception: Exception)
}