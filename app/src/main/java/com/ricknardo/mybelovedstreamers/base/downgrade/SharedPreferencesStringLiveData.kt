package com.ricknardo.mybelovedstreamers.base.downgrade

import android.content.SharedPreferences

class SharedPreferencesStringLiveData(sharedPreferences: SharedPreferences,
                                      key: String,
                                      defaultValue: String) : SharedPreferencesLiveData<String>(sharedPreferences, key, defaultValue) {

    override fun getValueFromPreferences(key: String, defaultValue: String): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

}