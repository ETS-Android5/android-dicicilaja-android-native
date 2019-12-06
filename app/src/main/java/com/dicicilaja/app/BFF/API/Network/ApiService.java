package com.dicicilaja.app.BFF.API.Network;

import com.dicicilaja.app.BFF.API.Data.Login.Login;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @POST("login")
    @FormUrlEncoded
    Call<Login> login(@Field("username") String username,
                      @Field("password") String password);
}
