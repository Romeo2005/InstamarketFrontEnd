package org.romeo.instamarketApp.activities.content.fragments.user

import android.graphics.Bitmap

class UserState(var username: String? = "input your username",
                var userImgBitmap: Bitmap? = null,
                var city: String? = "your city",
                var biography: String? = "your biography",
                var followersNumber: Int = 0) {

}