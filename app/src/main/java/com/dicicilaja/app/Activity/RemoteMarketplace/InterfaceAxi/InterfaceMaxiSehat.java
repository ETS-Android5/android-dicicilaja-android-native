package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemMaxiSehat.MaxiSehat;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by fawazrifqi on 13/05/18.
 */

public interface InterfaceMaxiSehat {
    @Headers({
            "Accept: application/json",
    })
    @GET("jodi/maxi/sehat")
    Call<MaxiSehat> getProduct(@Header("Authorization") String apiKey);
}
