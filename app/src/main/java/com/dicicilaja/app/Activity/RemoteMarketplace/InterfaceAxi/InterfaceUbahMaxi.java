package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemUbahMaxi.UbahMaxi;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by fawazrifqi on 06/05/18.
 */

public interface InterfaceUbahMaxi {
    @Headers({
            "Accept: application/json",
    })
    @POST("mitra/edit")
    @FormUrlEncoded

    Call<UbahMaxi> change(@Header("Authorization") String apiKey,
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
                          @Field("company_address") String company_address);
}
