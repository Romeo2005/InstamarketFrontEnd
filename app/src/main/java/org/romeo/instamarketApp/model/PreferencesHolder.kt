package org.romeo.instamarketApp.model

import androidx.appcompat.app.AppCompatActivity


/**
 * A class for keeping the preferences of an activity
 * to avoid using UI classes in Presenter/ViewModel
 * */
class PreferencesHolder(activity: AppCompatActivity) {
    private val preferences = activity.getPreferences(AppCompatActivity.MODE_PRIVATE)

    fun saveUser(username: String?, password: String?) {
        preferences.edit()
                .putString(USERNAME, username)
                .putString(PASSWORD, password)
                .apply()
    }

    val username: String?
        get() = preferences
                .getString(USERNAME, null)
    val password: String?
        get() = preferences
                .getString(PASSWORD, null)

    companion object {
        const val USERNAME = "USERNAME"
        const val PASSWORD = "PASSWORD"
    }
}