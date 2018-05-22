package com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemDetailProgramMaxi.DetailProgramMaxi;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by fawazrifqi on 10/05/18.
 */

public interface InterfaceDetailProgramMaxi {
    @Headers({
            "Accept: application/json",
    })
    @GET("jodi/program/maxi/{id}")
    Call<DetailProgramMaxi> getDetail(@Header("Authorization") String apiKey, @Path("id") String id);
}
