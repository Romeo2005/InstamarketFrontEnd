package org.romeo.instamarketApp.model

import org.brunocvcunha.instagram4j.requests.payload.InstagramUser
import org.romeo.instamarketApp.model.data_model.UsernamePassword
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/*https://stackoverflow.com/questions/39835164/retrofit-2-send-object-with-post*/
interface RetrofitService {

    @POST("getInstagramUser")
    fun getInstagramUser(@Body usernamePassword: UsernamePassword?): Call<InstagramUser?>
}