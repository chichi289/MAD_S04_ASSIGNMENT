package com.chichi289.week5.utils

import android.util.Log

fun String?.log() {
    Log.e("CHIRAG", this.toString())
}

fun String?.nullSafe() = this ?: ""
fun Int?.nullSafe() = this ?: 0