package com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemUbahPassword.UbahPassword;
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
    @POST("/changepassword")
    @FormUrlEncoded
    Call<UbahPassword> change(@Header("Authorization") String apiKey,
                              @Field("oldPassword") String oldPassword,
                              @Field("newPassword") String newPassword,
                              @Field("secondNewPassword") String secondNewPassword);
}
