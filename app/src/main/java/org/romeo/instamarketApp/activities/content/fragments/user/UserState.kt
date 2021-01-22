package org.romeo.instamarketApp.activities.content.fragments.user

import android.graphics.Bitmap

/**
 * Class that saves user state to transport it to transport it
 * from ViewModel to Fragment.
 * */
class UserState(var username: String? = "input your username",
                var userImgBitmap: Bitmap? = null,
                var city: String? = "your city",
                var biography: String? = "your biography",
                var followersNumber: Int = 0) {

}