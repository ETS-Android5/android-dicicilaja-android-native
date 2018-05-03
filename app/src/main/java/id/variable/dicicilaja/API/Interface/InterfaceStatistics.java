package id.variable.dicicilaja.API.Interface;

import id.variable.dicicilaja.API.Item.Statistics.Statistics;
import id.variable.dicicilaja.Model.ResRequestProcess;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by fawazrifqi on 03/05/18.
 */

public interface InterfaceStatistics {

    @Headers({
            "Accept: application/json",
    })
    @POST("jodi/statistics")
    Call<Statistics> statistics(@Header("Authorization") String apiKey);
}
