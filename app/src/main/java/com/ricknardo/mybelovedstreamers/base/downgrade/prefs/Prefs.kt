package com.ricknardo.mybelovedstreamers.base.downgrade.prefs

import android.content.Context
import android.content.SharedPreferences

class Prefs (context: Context) {
    val PREFS_NAME = "com.ricknardo.mybelovedstreamers.twitchtoken"
    val SHARED_NAME = "shared_token"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)

    var token: String?
        get() = prefs.getString(SHARED_NAME, "")
        set(value) = prefs.edit().putString(SHARED_NAME, value).apply()
}

