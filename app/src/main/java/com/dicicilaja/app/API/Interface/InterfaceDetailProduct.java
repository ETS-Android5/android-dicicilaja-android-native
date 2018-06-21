package com.dicicilaja.app.API.Interface;

import com.dicicilaja.app.API.Item.DetailProduct.DetailProduct;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by ziterz on 06/04/18.
 */

public interface InterfaceDetailProduct {
    @Headers({
            "Accept: application/json",
    })
    @GET("product/{id}")
    Call<DetailProduct> getDetailProduct(@Path("id") int id);
}
