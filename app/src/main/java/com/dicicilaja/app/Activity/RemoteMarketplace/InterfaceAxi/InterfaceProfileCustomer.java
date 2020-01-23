package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;


import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemProfileCustomer.ProfileCustomer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface InterfaceProfileCustomer {
    @Headers({
            "Accept: application/json",
    })
    @GET("bffnew/customer/profile/")
    Call<ProfileCustomer> getProfile(@Header("Authorization") String apiKey);
}
