package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemProgramMaxi.ProgramMaxi;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by fawazrifqi on 06/05/18.
 */

public interface InterfaceProgramMaxi {
    @Headers({
            "Accept: application/json",
    })
    @GET("mitra/product")
    Call<ProgramMaxi> getProgramMaxi(@Header("Authorization") String apiKey);
}
