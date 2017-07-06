package com.ilaps.androidtest.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by ricar on 6/7/17.
 */
fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}