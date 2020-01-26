package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.app.Activity.RemoteMarketplace.ItemBFF.ProfileAxi.ProfileAxi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by fawazrifqi on 05/05/18.
 */

public interface InterfaceProfileAxi {
    @Headers({
            "Accept: application/json",
    })
    @GET("bffnew/axi/profile")
    Call<ProfileAxi> getProfile(@Header("Authorization") String apiKey);
}
