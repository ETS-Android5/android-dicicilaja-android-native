package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemCreateCustomer.CreateCustomer;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface InterfaceCreateMitra {
    @Headers({
            "Accept: application/json",
    })
    @POST("register/mitra")
    @FormUrlEncoded
    Call<CreateCustomer> create(@Header("Authorization") String apiKey,
                                @Field("company") String company,
                                @Field("owner") String owner,
                                @Field("gender") String gender,
                                @Field("address") String address,
                                @Field("handphone") String handphone,
                                @Field("telphone") String telphone,
                                @Field("ktp") String ktp,
                                @Field("personal_npwp") String personal_npwp,
                                @Field("company_npwp") String company_npwp,
                                @Field("hometown") String hometown,
                                @Field("city") String city,
                                @Field("company_address") String company_address,
                                @Field("email") String email,
                                @Field("password") String password,
                                @Field("program") String program,
                                @Field("program_id") String program_id,
                                @Field("ktp_image") String ktp_image,
                                @Field("npwp_image") String npwp_image);
}
