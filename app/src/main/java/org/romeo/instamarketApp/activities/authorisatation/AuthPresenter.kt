package org.romeo.instamarketApp.activities.authorisatation

import android.os.Handler
import android.os.HandlerThread
import org.romeo.instamarketApp.R
import org.romeo.instamarketApp.model.PreferencesHolder
import org.romeo.instamarketApp.model.Repository.getInstagramUserFor

class AuthPresenter(private val activity: AuthActivityTemplate,
                    private val preferences: PreferencesHolder) {

    private val userHandler: Handler

    /**
     * Connected to xml view with two-way-binding.
     * */
    var username = ""
    var password = ""

    init {
        val handlerThread = HandlerThread("USER_HANDLER_THREAD")
        handlerThread.start()
        userHandler = Handler(handlerThread.looper)
        initialize()
    }


    /**
     * If user has been already entered in the app,
     * than activity will not be loaded.
     * The ContentActivity will be immediately started.
     * */
    private fun initialize() {
        userHandler.post {
            val username = preferences.username
            val password = preferences.password

            if (username.isNullOrEmpty() || password.isNullOrEmpty())
                activity.initialize()
            else {
                val user = getInstagramUserFor(username, password)

                user?.let { userNotNull ->
                    activity.loadContentActivity(userNotNull)
                } ?: run {
                    activity.initialize()
                }
            }
        }
    }

    /**
     * Saves user into SharedPreferences or shows the error
     * in case of wrong data.
     * */
    fun saveUser() {
        userHandler.post {
            val user = getInstagramUserFor(username, password)
            user?.let { userNotNull ->

                preferences.saveUser(username, password)
                activity.loadContentActivity(userNotNull)

                return@let // Не уверен нащет уместности этого выражения.
            }

            activity.showError(R.string.incorrect_auth_data)
        }
    }
}