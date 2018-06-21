package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemRecommendation.Recommendation;
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
    @GET("recommendation")
    Call<Recommendation> getRecommend();
}
