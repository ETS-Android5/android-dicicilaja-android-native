package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemDetailProgramMaxi.DetailProgramMaxi;

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
    @GET("product/{subprodukid}")
    Call<DetailProgramMaxi> getDetail(@Header("Authorization") String apiKey, @Path("subprodukid") String id);
}
