package com.dicicilaja.app.API.Interface;

import com.dicicilaja.app.API.Item.Login.Login;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemMarketplace.LoginObj;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceLogin {
    @POST("login")
    @FormUrlEncoded
    Call<Login> login(@Field("username") String username,
                      @Field("password") String password);
}
