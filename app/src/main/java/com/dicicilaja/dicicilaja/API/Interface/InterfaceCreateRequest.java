package com.dicicilaja.dicicilaja.API.Interface;

import com.dicicilaja.dicicilaja.API.Item.CreateRequest.CreateRequest;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by fawazrifqi on 25/04/18.
 */

public interface InterfaceCreateRequest {
    @Headers({
            "Accept: application/json",
    })
    @Multipart
    @POST("request")
    Call<CreateRequest> assign(@Header("Authorization") String apiKey,
                               @Part("program_id") String program_id,
                               @Part("colleteral_id") String colleteral_id,
                               @Part("status_id") String status_id,
                               @Part("manufacturer") String manufacturer,
                               @Part("year") String year,
                               @Part("tenor") String tenor,
                               @Part("amount") String amount,
                               @Part("qty") String qty,
                               @Part("area_id") String area_id,
                               @Part("branch_id") String branch_id,
                               @Part("client_name") String client_name,
                               @Part("hp") String hp,
                               @Part("address") String address,
                               @Part("district") String district,
                               @Part("city") String city,
                               @Part("province") String province,
                               @Part("email") String email,
                               @Part MultipartBody.Part file_ktp,
                               @Part MultipartBody.Part file_colleteral);
}
