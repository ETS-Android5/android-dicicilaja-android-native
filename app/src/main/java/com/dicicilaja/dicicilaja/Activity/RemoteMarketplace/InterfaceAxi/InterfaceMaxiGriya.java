package com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemMaxiGriya.MaxiGriya;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by fawazrifqi on 13/05/18.
 */

public interface InterfaceMaxiGriya {
    @Headers({
            "Accept: application/json",
    })
    @GET("jodi/maxi/griya")
    Call<MaxiGriya> getProduct(@Header("Authorization") String apiKey);
}
