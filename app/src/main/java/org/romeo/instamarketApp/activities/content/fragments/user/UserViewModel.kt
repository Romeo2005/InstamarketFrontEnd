package org.romeo.instamarketApp.activities.content.fragments.user

import android.os.Handler
import android.os.HandlerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser
import org.romeo.instamarketApp.model.Repository

class UserViewModel(arguments: FragmentArgumentsHolder) : ViewModel() {

    private val instagramUser: InstagramUser = Gson().fromJson(
            arguments.instagramUserJSON,
            InstagramUser::class.java)

    private val picassoHandler: Handler = run {
        val handlerThread = HandlerThread("PICASSO_HANDLER_THREAD")
        handlerThread.start()
        Handler(handlerThread.looper)
    }

    private val userData = MutableLiveData<UserState>()

    init {
        updateUserDataValue()
    }

    private fun updateUserDataValue() {

        val userState = UserState()

        picassoHandler.post {
            userState.userImgBitmap = Repository
                    .getImageFromUrl(instagramUser.getHd_profile_pic_url_info().url)
        }

        userState.biography = instagramUser.biography
        userState.username = instagramUser.username
        userState.followersNumber = instagramUser.follower_count
        userState.city = instagramUser.city_name

        userData.value = userState
    }

    fun getUserData() = userData as LiveData<UserState>
}