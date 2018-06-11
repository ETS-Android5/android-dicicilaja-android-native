package com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemProfileMaxi.ProfileMaxi;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by fawazrifqi on 06/05/18.
 */

public interface InterfaceProfileMaxi {
    @Headers({
            "Accept: application/json",
    })
    @GET("mitra/profile")
    Call<ProfileMaxi> getProfile(@Header("Authorization") String apiKey);
}
