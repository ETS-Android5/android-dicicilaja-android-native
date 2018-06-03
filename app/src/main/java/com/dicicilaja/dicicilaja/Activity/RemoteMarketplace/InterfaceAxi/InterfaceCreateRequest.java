package com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemCreateRequest.CreateRequest;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by fawazrifqi on 11/05/18.
 */

public interface InterfaceCreateRequest {
    @Headers({
            "Accept: application/json",
    })
    @POST("transaction")
    @FormUrlEncoded
    Call<CreateRequest> assign(@Header("Authorization") String apiKey,
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
                               @Part MultipartBody.Part ktp_image,
                               @Part MultipartBody.Part colleteral_image);
}
