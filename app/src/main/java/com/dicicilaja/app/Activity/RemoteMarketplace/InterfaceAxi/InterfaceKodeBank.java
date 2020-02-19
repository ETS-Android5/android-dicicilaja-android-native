package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;


import com.dicicilaja.app.Activity.RemoteMarketplace.Item.KodeBank.KodeBank;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface InterfaceKodeBank {
    @GET("profile/banks?page[size]=100")
    Call<KodeBank> getKodeBank();
}
