package com.tsu.wordsfactory.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences


private const val IS_FIRST_RUN = "isFirstRun"

class Preferences (private val context: Context) {

    fun isFirstRun(): Boolean {
        val sharedPref = context.getSharedPreferences(IS_FIRST_RUN, Context.MODE_PRIVATE)

        val isFirst = sharedPref.getBoolean(IS_FIRST_RUN, true)

        if (isFirst) {
            val edit =  sharedPref.edit()
            edit.putBoolean(IS_FIRST_RUN, false)
            edit.apply()
        }
        return isFirst
    }
}