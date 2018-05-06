package id.variable.dicicilaja.API.Interface;

import id.variable.dicicilaja.API.Item.DetailPengajuanResponse;
import id.variable.dicicilaja.API.Item.DetailRequest.DetailRequest;
import id.variable.dicicilaja.API.Item.RequestDetail.RequestDetail;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by ziterz on 1/29/2018.
 */

public interface InterfaceDetailRequest {
    @Headers({
            "Accept: application/json",
    })
    @GET("jodi/detailpengajuan/{id}")
    Call<RequestDetail> getDetailRequest(@Header("Authorization") String apiKey, @Path("id") int id);
}
