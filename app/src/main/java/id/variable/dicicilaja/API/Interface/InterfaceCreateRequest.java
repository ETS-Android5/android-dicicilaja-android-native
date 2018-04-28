package id.variable.dicicilaja.API.Interface;

import id.variable.dicicilaja.API.Item.CreateRequest.CreateRequest;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by fawazrifqi on 25/04/18.
 */

public interface InterfaceCreateRequest {
    @Headers({
            "Accept: application/json",
    })
    @POST("request")
    @FormUrlEncoded
    Call<CreateRequest> assign(@Header("Authorization") String apiKey,
                               @Field("axi_id") String axi_id,
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
                               @Field("colleteral_image") String colleteral_image);
}
