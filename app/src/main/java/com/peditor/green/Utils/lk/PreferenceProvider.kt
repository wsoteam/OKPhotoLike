package com.peditor.green.Utils.lk

import android.content.Context
import android.content.SharedPreferences
import com.peditor.green.App

class PreferenceProvider {
    private fun instance(): SharedPreferences? {
        return App.getInstance().getSharedPreferences(SharedKeys.GLOBAL_KEY.global, Context.MODE_PRIVATE)
    }

    private fun editor(put: (SharedPreferences.Editor?) -> SharedPreferences.Editor?) =
            put(instance()?.edit())?.apply()

    fun saveDataInt(key: String?, value: Int?) {
        editor { it?.putInt(key, value!!) }
    }

    fun getDataInt(str: String): Int {
        return instance()?.getInt(str, 0)!!
    }

    fun saveDataString(key: String?, value: String?) {
        editor { it?.putString(key, value) }
    }

    fun getDataString(str: String): String {
        return instance()?.getString(str, LideraSharedKeys.AppCheckerWord.key)!!
    }
}