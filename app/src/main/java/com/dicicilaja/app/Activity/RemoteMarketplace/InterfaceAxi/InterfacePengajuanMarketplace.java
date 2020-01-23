package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemAllPengajuan.AllPengajuan;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by fawazrifqi on 16/05/18.
 */

public interface InterfacePengajuanMarketplace {
    @Headers({
            "Accept: application/json",
    })
    @GET("bffnew/user/request")
    Call<AllPengajuan> getPengajuan(@Header("Authorization") String apiKey);
}
