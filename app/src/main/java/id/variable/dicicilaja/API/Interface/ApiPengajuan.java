package id.variable.dicicilaja.API.Interface;

import id.variable.dicicilaja.API.Item.PengajuanResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
/**
 * Created by ziterz on 30/12/2017.
 */

public interface ApiPengajuan {

    @GET("movie/popular")
    Call<PengajuanResponse> getPengajuan(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<PengajuanResponse> getDetailPengajuan(@Path("id") int id, @Query("api_key") String apiKey);

}
