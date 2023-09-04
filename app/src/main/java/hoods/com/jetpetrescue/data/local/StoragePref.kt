package hoods.com.jetpetrescue.data.local

import android.content.Context
import hoods.com.jetpetrescue.R
import hoods.com.jetpetrescue.data.network.K

class StoragePref(context: Context) {
    private val pref = context
        .getSharedPreferences(
            context.getString(R.string.app_name),
            Context.MODE_PRIVATE
        )

    fun getToken(): String =
        pref.getString(K.USER_TOKEN, null) ?: ""

    fun saveToken(token: String?) {
        pref.edit().apply {
            putString(K.USER_TOKEN, token)
            apply()
        }
    }


}