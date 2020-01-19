package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemUbahAxi.UbahAxi;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemUbahCustomer.UbahCustomer;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InterfaceUbahCustomer {
    @Headers({
            "Accept: application/json",
    })
    @POST("customer/edit/{id}")
    @FormUrlEncoded
    Call<UbahCustomer> change(@Header("Authorization") String apiKey,
                              @Field("name") String name,
                              @Field("phone") String phone,
                              @Field("birth_date") String birth_date,
                              @Field("address") String address,
                              @Field("subdistrict") String subdistrict,
                              @Field("district") String district,
                              @Field("city") String city,
                              @Field("province") String province,
                              @Field("gender") String gender,
                              @Field("bill_number") String bill_number,
                              @Path("id") String id);
}
