package id.variable.dicicilaja.API.Interface;

import id.variable.dicicilaja.API.Item.PengajuanAxi.PengajuanAxi;
import id.variable.dicicilaja.API.Item.PengajuanMaxi.PengajuanMaxi;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by fawazrifqi on 06/05/18.
 */

public interface InterfacePengajuanMaxi {
    @Headers({
            "Accept: application/json",
    })
    @GET("jodi/pengajuan/maxi")
    Call<PengajuanMaxi> getPengajuanMaxi(@Header("Authorization") String apiKey);
}
