package com.itsydev.clubvr.utils

import android.content.Context

fun Context.setSharedPreference(prefsName: String, key: String, value: String) {
    getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        .edit().apply {
            putString(key, value); apply()
        }
}

fun Context.getSharedPreference(prefsName: String, key: String): String {
    getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        ?.getString(key, "Value is empty!")?.let { return it }
    return "Preference doesn't exist."
}