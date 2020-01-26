package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.app.Activity.RemoteMarketplace.ItemBFF.ProfileAxi.ProfileAxi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface InterfaceProfile{
    @Headers({
            "Accept: application/json",
    })
    @GET("bffnew/axi/profile")
    Call<ProfileAxi> getProfile(@Header("Authorization") String apiKey);
}
