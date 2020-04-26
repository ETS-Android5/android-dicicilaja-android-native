package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.app.Activity.RemoteMarketplace.ItemBFF.ProfileAxi.ProfileAxi;
import com.dicicilaja.app.InformAXI.model.ShProfile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface InterfaceProfile{
    @Headers({
            "Accept: application/json",
    })
    @GET("axi/profile")
    Call<ProfileAxi> getProfile(@Header("Authorization") String apiKey);

    @GET("sh/profile")
    Call<ShProfile>
    getShProfile(@Header("Authorization") String authorization);
}
