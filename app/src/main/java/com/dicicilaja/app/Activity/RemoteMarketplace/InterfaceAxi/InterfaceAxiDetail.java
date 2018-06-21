package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemAxiDetail.AXIDetail;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by fawazrifqi on 29/04/18.
 */

public interface InterfaceAxiDetail {
    @Headers({
            "Accept: application/json",
    })
    @GET("axi/detail")
    Call<AXIDetail> getDetail(@Header("Authorization") String apiKey);
}
