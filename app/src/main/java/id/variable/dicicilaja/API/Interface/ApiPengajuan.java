package id.variable.dicicilaja.API.Interface;

import java.util.List;

import id.variable.dicicilaja.API.Item.PengajuanResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by ziterz on 30/12/2017.
 */

public interface ApiPengajuan {

    @Headers({
            "Accept: application/json",
    })
    @GET("request/{user_id}")
    Call<PengajuanResponse> getPengajuan(@Header("Authorization") String apiKey);

}
