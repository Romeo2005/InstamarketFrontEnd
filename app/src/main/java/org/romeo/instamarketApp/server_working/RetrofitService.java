package org.romeo.instamarketApp.server_working;

/*https://stackoverflow.com/questions/39835164/retrofit-2-send-object-with-post*/

import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface RetrofitService {
    @GET("getInstagramUser")
    Call<InstagramUser> getInstagramUser(@Query("username") String username, @Query("password") String password);

/*    @GET("data/2.5/onecall")
    Call<InstagramUser> getInstagramUser(@Query("username") String username, @Query("password") String password);*/
}
