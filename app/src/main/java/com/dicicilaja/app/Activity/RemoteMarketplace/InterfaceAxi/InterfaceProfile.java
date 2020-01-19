package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemProfileAxi.ProfileAxi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface InterfaceProfile{
    @Headers({
            "Accept: application/json",
    })
    @GET("user/profile/{id}")
    Call<ProfileAxi> getProfile(@Header("Authorization") String apiKey,
                                @Path("id") String id);
}
