package org.romeo.instamarketApp.activities.authorisatation

import android.os.Handler
import android.os.HandlerThread
import org.romeo.instamarketApp.R
import org.romeo.instamarketApp.model.CashPreferences
import org.romeo.instamarketApp.model.Repository.getInstagramUserFor

class AuthPresenter(private val activity: AuthActivityTemplate,
                    private val preferences: CashPreferences) {

    private val userHandler: Handler

    var username = ""
    var password = ""

    init {
        val handlerThread = HandlerThread("USER_HANDLER_THREAD")
        handlerThread.start()
        userHandler = Handler(handlerThread.looper)
        initialize()
    }

    private fun initialize() {
        userHandler.post {
            val username = preferences.username
            val password = preferences.password

            if (username.isNullOrEmpty() || password.isNullOrEmpty())
                activity.initialize()
            else {
                val user = getInstagramUserFor(username, password)

                user?.let { user ->
                    activity.loadContentActivity(user)
                } ?: run {
                    activity.initialize()
                }
            }
        }
    }

    fun saveUser() {
        userHandler.post {
            val user = getInstagramUserFor(username, password)
            user?.let { user ->

                preferences.saveUser(username, password)
                activity.loadContentActivity(user)

                return@let
            }

            activity.showError(R.string.incorrect_auth_data)
        }
    }
}