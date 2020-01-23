package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemRekanBisnis.RekanBisnis;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by fawazrifqi on 06/05/18.
 */

public interface InterfaceRekanBisnis {
    @Headers({
            "Accept: application/json",
    })
    @GET("axi/rekanbisnis/{nomor_axi_id}")
    Call<RekanBisnis> getRekanBisnis(@Header("Authorization") String apiKey,
                                     @Path("nomor_axi_id") String nomor_axi_id);
}
