package com.ilaps.androidtest.validators

import android.widget.EditText
import java.util.regex.Pattern

/**
 * Created by ricar on 5/7/17.
 */
fun EditText.validateIsBlank(): Boolean {
    return this.text.toString().isBlank()
}

fun EditText.validateIsEmail(): Boolean {
    val EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    val emailPattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE)
    return emailPattern.matcher(this.text.toString()).matches()
}
