package com.dicicilaja.app.API.Interface;

import com.dicicilaja.app.Model.ResObj;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ziterz on 23/01/2018.
 */

public interface InterfaceProsesPengajuan {
    @POST("assign")
    @FormUrlEncoded
    Call<ResObj> login(@Field("username") String username,
                       @Field("password") String password);
}
