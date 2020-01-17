package com.dicicilaja.app.BusinessReward.network;

import com.dicicilaja.app.BusinessReward.dataAPI.delCart.DelCart;
import com.dicicilaja.app.BusinessReward.dataAPI.getCart.GetCart;
import com.dicicilaja.app.BusinessReward.dataAPI.postCart.PostCart;
import com.dicicilaja.app.BusinessReward.dataAPI.status.Status;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService3 {
    @Headers({
            "Accept: application/json",
    })

    @GET("cart")
    Call<GetCart> getCart(@Header("Authorization") String apiKey);

    @POST("cart")
    @FormUrlEncoded
    Call<PostCart> postCart(@Header("Authorization") String apiKey,
                            @Field("productId") String productId,
                            @Field("operation") String operation);

    @DELETE("cart/{id}")
    Call<DelCart> delCart(@Header("Authorization") String apiKey,
                          @Path("id") int id);
}
