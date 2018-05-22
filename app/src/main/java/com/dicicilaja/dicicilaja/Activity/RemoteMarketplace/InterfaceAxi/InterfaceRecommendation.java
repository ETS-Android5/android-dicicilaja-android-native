package com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemRecommendation.Recommendation;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by fawazrifqi on 13/05/18.
 */

public interface InterfaceRecommendation {
    @Headers({
            "Accept: application/json",
    })
    @GET("jodi/recommendation")
    Call<Recommendation> getRecommend();
}
