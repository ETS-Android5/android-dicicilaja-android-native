package com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemMaxiExtraguna.MaxiExtraguna;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by fawazrifqi on 13/05/18.
 */

public interface InterfaceMaxiExtraguna {
    @Headers({
            "Accept: application/json",
    })
    @GET("jodi/maxi/extraguna")
    Call<MaxiExtraguna> getProduct(@Header("Authorization") String apiKey);
}
