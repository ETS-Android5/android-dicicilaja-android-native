package id.variable.dicicilaja.API.Interface;

import id.variable.dicicilaja.API.Item.DetailPengajuanResponse;
import id.variable.dicicilaja.API.Item.DetailPengajuanStatus;
import id.variable.dicicilaja.API.Item.DetailPengajuanStatusResponse;
import id.variable.dicicilaja.API.Item.PengajuanResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by ziterz on 1/15/2018.
 */

public interface InterfaceDetailPengajuanStatus {
    @Headers({
            "Accept: application/json",
    })
    @GET("request/{id}/status")
    Call<DetailPengajuanStatus> getDetailPengajuanStatus(@Header("Authorization") String apiKey, @Path("id") int id);
}
