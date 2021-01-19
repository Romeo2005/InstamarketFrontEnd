package org.romeo.instamarketApp.model

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import org.romeo.instamarketApp.activities.authorisatation.AuthPresenter

class CashPreferences(activity: AppCompatActivity) {
    private val preferences: SharedPreferences = activity.getPreferences(AppCompatActivity.MODE_PRIVATE)
    fun saveUser(username: String?, password: String?) {
        preferences.edit()
                .putString(USERNAME, username)
                .putString(PASSWORD, password)
                .apply()
    }

    val username: String?
        get() = preferences
                .getString(USERNAME, AuthPresenter.DEFAULT_AUTH_VALUE)
    val password: String?
        get() = preferences
                .getString(PASSWORD, AuthPresenter.DEFAULT_AUTH_VALUE)

    companion object {
        const val USERNAME = "USERNAME"
        const val PASSWORD = "PASSWORD"
    }

}