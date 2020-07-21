package com.asynctaskcoffee.moviestemplate.utils

import android.content.SharedPreferences
import javax.inject.Inject

class MoviesPrefManager @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun save(key: String, value: Any?) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        when (value) {
            is Int -> editor.putInt(key, value)
            is String -> editor.putString(key, value)
            is Boolean -> editor.putBoolean(key, value)
            is Long -> editor.putLong(key, value)
            else -> return
        }
        editor.apply()
    }

    fun getStringVal(key: String, defValue: String = "") =
        sharedPreferences.getString(key, defValue)

    fun getBooleanVal(key: String, defValue: Boolean = false) =
        sharedPreferences.getBoolean(key, defValue)

    fun getIntVal(key: String, defValue: Int = -1) = sharedPreferences.getInt(key, defValue)
}