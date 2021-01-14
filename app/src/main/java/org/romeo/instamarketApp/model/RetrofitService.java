package org.romeo.instamarketApp.model;

/*https://stackoverflow.com/questions/39835164/retrofit-2-send-object-with-post*/

import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {

    //I know that transporting passwords in url is not good practice, it's just for test
    @GET("getInstagramUser")
    Call<InstagramUser> getInstagramUser(@Query("username") String username, @Query("password") String password);

/*    @GET("data/2.5/onecall")
    Call<InstagramUser> getInstagramUser(@Query("username") String username, @Query("password") String password);*/
}
