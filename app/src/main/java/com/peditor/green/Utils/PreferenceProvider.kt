package com.peditor.green.Utils

import android.content.Context
import android.content.SharedPreferences
import com.peditor.green.App

object PreferenceProvider {

    var sp: SharedPreferences = App.getInstance()
            .getSharedPreferences("", Context.MODE_PRIVATE)

    var editor = sp.edit()

    private const val KEY_LAST_URL = "KEY_LAST_URL"
    fun setLastURL(url: String) {
        editor.putString(
                KEY_LAST_URL, url
        )
        editor.commit()
    }

    fun getLastURL(): String {
        val url = sp.getString(
                KEY_LAST_URL,
                ""
        )!!
        return url
    }

    private const val KEY_FOR_URL = "KEY_FOR_URL"
    const val EMPTY_URL = "EMPTY_URL"
    const val NOT_EMPTY_URL = "NOT_EMPTY_URL"
    fun saveUrl(url: String) {
        editor.putString(
                KEY_FOR_URL, url
        )
        editor.commit()
    }

    fun getUrl(): String {
        val saveText = sp.getString(
                KEY_FOR_URL,
                EMPTY_URL
        )!!
        return saveText
    }
}