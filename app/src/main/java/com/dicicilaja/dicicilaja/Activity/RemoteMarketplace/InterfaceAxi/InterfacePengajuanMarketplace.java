package com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by fawazrifqi on 16/05/18.
 */

public interface InterfacePengajuanMarketplace {
    @Headers({
            "Accept: application/json",
    })
    @GET("jodi/recommendation")
    Call<com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemRequestMarketplace.Recommendation> getRecommend();
}
