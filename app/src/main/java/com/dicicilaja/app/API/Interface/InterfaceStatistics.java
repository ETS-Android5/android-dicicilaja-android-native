package com.dicicilaja.app.API.Interface;

import com.dicicilaja.app.API.Item.Statistics.Statistics;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by fawazrifqi on 03/05/18.
 */

public interface InterfaceStatistics {

    @Headers({
            "Accept: application/json",
    })
    @GET("statistic")
    Call<Statistics> statistics(@Header("Authorization") String apiKey);
}
