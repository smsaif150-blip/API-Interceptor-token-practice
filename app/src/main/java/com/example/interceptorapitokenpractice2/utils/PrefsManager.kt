package com.example.interceptorapitokenpractice2.utils

import android.content.Context

class PrefsManager(context: Context) {

    val prefs = context.getSharedPreferences("MY_APP", Context.MODE_PRIVATE)

    fun saveToken(token: String)
    {
        prefs.edit().putString("TOKEN",token).apply()
    }

    fun getToken(): String? {
        return prefs.getString("TOKEN",null)
    }

}