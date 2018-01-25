package id.variable.dicicilaja.API.Interface;

import id.variable.dicicilaja.API.Item.PengajuanProgressResponse;
import id.variable.dicicilaja.API.Item.PengajuanResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by ziterz on 1/25/2018.
 */

public interface InterfacePengajuanProgress {
    @Headers({
            "Accept: application/json",
    })
    @GET("request/progress")
    Call<PengajuanProgressResponse> getPengajuanProgress(@Header("Authorization") String apiKey);
}
