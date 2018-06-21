package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemMaxiProgram.MaxiProgram;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by fawazrifqi on 13/05/18.
 */

public interface InterfaceMaxiProgram {
    @Headers({
            "Accept: application/json",
    })
    @GET("maxi/{slug}")
    Call<MaxiProgram> getProduct(@Header("Authorization") String apiKey, @Path("slug") String slug);
}
