package com.dicicilaja.app.Remote;

import com.dicicilaja.app.API.Model.AreaRequest.CompleteDataUpdate;
import com.dicicilaja.app.API.Model.CheckVersion;
import com.dicicilaja.app.BFF.API.Data.Login.Login;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ziterz on 11/29/2017.
 */

public interface UserService {

    @POST("login")
    @FormUrlEncoded
    Call<Login> login(@Field("username") String username,
                      @Field("password") String password);


    @GET("version")
    Call<CheckVersion> checkVersion();

    @PATCH("user/{id}/phone")
    @FormUrlEncoded
    Call<CompleteDataUpdate> updatePhoneNumber(@Path("id") String id,
                                @Field("phone") String phone);

    @PATCH("user/{id}/email")
    @FormUrlEncoded
    Call<CompleteDataUpdate> updateEmail(@Path("id") String id,
                                         @Field("email") String email );
}
