package com.dicicilaja.app.Remote;

import com.dicicilaja.app.API.Item.Login.Login;
import com.dicicilaja.app.Model.ResObj;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ziterz on 11/29/2017.
 */

public interface UserService {

    @POST("login")
    @FormUrlEncoded
    Call<Login> login(@Field("username") String username,
                      @Field("password") String password);



}
