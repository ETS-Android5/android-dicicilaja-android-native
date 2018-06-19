package com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemCreateAXI.CreateAXI;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemCreateCustomer.CreateCustomer;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface InterfaceCreateCustomer {
    @Headers({
            "Accept: application/json",
    })
    @POST("register/customer")
    @FormUrlEncoded
    Call<CreateCustomer> create(@Field("email") String email,
                                @Field("password") String password,
                                @Field("name") String name,
                                @Field("phone") String phone,
                                @Field("area") String area,
                                @Field("branch") String branch,
                                @Field("address") String address,
                                @Field("province") String province,
                                @Field("city") String city,
                                @Field("district") String district,
                                @Field("subdistrict") String subdistrict,
                                @Field("gender") String gender,
                                @Field("birth_date") String birth_date);
}
