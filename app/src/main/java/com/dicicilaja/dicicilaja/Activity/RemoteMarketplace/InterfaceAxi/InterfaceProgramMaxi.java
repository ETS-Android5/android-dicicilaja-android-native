package com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemProgramMaxi.ProgramMaxi;
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
    @GET("jodi/program/maxi")
    Call<ProgramMaxi> getProgramMaxi(@Header("Authorization") String apiKey);
}
