package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemUbahAxi.UbahAxi;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemUbahCustomer.UbahCustomer;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InterfaceUbahSh {
    @Headers({
            "Accept: application/json",
    })
    @POST("sh/edit")
    @FormUrlEncoded
    Call<UbahCustomer> change(@Header("Authorization") String apiKey,
                              @Field("name") String name,
                              @Field("email") String email,
                              @Field("phone") String phone);
}
