package com.dicicilaja.dicicilaja.Remote;

import com.dicicilaja.dicicilaja.Model.ResObj;
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
    Call<ResObj> login(@Field("username") String username,
                        @Field("password") String password);



}
