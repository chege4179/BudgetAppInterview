package com.peterchege.dummyproject.core.util

import android.content.Context
import android.widget.Toast

fun String.isSpecialCharString(): Boolean {
    return all { !it.isLetterOrDigit() }
}


fun getTodaysDate(): String {
    val formatter = java.text.SimpleDateFormat("dd/MM/yyyy")
    val date = java.util.Date()
    return formatter.format(date)
}


fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}