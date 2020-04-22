package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemCreateCustomer.CreateCustomer;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface InterfaceCreateCustomer {
    @POST("nasabah/registration")
    @FormUrlEncoded
    Call<CreateCustomer> create(@Header("Authorization") String apiKey,
                                @Field("nama") String nama,
                                @Field("email") String email,
                                @Field("no_hp") String no_hp,
                                @Field("password") String password,
                                @Field("password_confirmation") String password_confirmation);
}
