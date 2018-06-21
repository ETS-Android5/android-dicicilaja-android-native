package com.dicicilaja.app.API.Interface;

import com.dicicilaja.app.API.Item.CreateRequest.CreateRequest;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemCreateRequestMitra.CreateRequestMitra;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface InterfaceCreateRequestMitra {
    @Headers({
            "Accept: application/json",
    })
    @POST("request")
    @FormUrlEncoded
    Call<CreateRequestMitra> assign(@Header("Authorization") String apiKey,
                                    @Field("axi_reff") String axi_reff,
                                    @Field("channel_id") String channel_id,
                                    @Field("program_id") String program_id,
                                    @Field("colleteral_id") String colleteral_id,
                                    @Field("status_id") String status_id,
                                    @Field("manufacturer") String manufacturer,
                                    @Field("year") String year,
                                    @Field("tenor") String tenor,
                                    @Field("amount") String amount,
                                    @Field("qty") String qty,
                                    @Field("area_id") String area_id,
                                    @Field("branch_id") String branch_id,
                                    @Field("client_name") String client_name,
                                    @Field("hp") String hp,
                                    @Field("address") String address,
                                    @Field("district") String district,
                                    @Field("city") String city,
                                    @Field("province") String province,
                                    @Field("email") String email,
                                    @Field("ktp_image") String ktp_image,
                                    @Field("colleteral_image") String colleteral_image,
                                    @Field("id_partner") String id_partner,
                                    @Field("product") String product);
}
