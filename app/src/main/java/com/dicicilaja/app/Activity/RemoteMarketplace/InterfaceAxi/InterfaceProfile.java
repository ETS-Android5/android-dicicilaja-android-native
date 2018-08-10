package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemProfileAxi.ProfileAxi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface InterfaceProfile{
    @Headers({
            "Accept: application/json",
    })
    @GET("user/profile")
    Call<ProfileAxi> getProfile(@Header("Authorization") String apiKey);
}
