package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemAllProduct.AllProduk;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemPromo.Promo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface InterfaceAllProduk {
    @Headers({
            "Accept: application/json",
    })
    @GET("bffnew/product")
    Call<AllProduk> getProduct();
}
