package com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemMaxiUsaha.MaxiUsaha;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by fawazrifqi on 13/05/18.
 */

public interface InterfaceMaxiUsaha {
    @Headers({
            "Accept: application/json",
    })
    @GET("jodi/maxi/usaha")
    Call<MaxiUsaha> getProduct(@Header("Authorization") String apiKey);
}
