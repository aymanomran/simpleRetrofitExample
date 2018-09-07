package com.example.ayman.training1;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by AYMAN on 06/09/2018.
 */

public interface RetrofiteInterface {
    String Base_URL="http://developerhendy.16mb.com/";
    @GET("getposts.php")
    Call<List<Endi>>getposts();

    @FormUrlEncoded
    @POST("insertuserwithpost.php")
    Call<ResponseBody>setPost(@Field("username")String username
                      , @Field("password")String password
                      , @Field("email")String email
                      , @Field("address")String address
                       );


}

