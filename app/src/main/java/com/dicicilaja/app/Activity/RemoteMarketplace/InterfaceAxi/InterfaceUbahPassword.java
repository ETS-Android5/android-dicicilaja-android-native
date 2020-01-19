package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemUbahPassword.UbahPassword;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by fawazrifqi on 05/05/18.
 */

public interface InterfaceUbahPassword {
    @Headers({
            "Accept: application/json",
    })
    @POST("auth/password/change")
    @FormUrlEncoded
    Call<UbahPassword> change(@Header("Authorization") String apiKey,
                              @Field("old_password") String old_password,
                              @Field("password") String password,
                              @Field("new_password") String new_password);
}
