package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;


import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemCreateRequest.CreateRequest;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InterfaceFavorite {
    @Headers({
            "Accept: application/json",
    })
    @POST("product/favorite/{id}")
    @FormUrlEncoded
    Call<CreateRequest> assign(@Header("Authorization") String apiKey,
                               @Field("product_id") String product_id,
                               @Path("id") String id);
}
