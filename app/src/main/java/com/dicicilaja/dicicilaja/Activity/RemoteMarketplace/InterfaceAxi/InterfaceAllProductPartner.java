package com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemAllProductPartner.AllProductPartner;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemRecommendation.Recommendation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface InterfaceAllProductPartner {
    @Headers({
            "Accept: application/json",
    })
    @GET("partner/{id}")
    Call<AllProductPartner> getProduct(@Path("id") String id);
}
