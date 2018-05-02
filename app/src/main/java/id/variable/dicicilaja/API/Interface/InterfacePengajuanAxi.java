package id.variable.dicicilaja.API.Interface;

import id.variable.dicicilaja.API.Item.PengajuanAxi.PengajuanAxi;
import id.variable.dicicilaja.API.Item.Request.Request;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by fawazrifqi on 29/04/18.
 */

public interface InterfacePengajuanAxi {
    @Headers({
            "Accept: application/json",
    })
    @GET("jodi/pengajuan/axi")
    Call<PengajuanAxi> getPengajuanAxi(@Header("Authorization") String apiKey);

}
