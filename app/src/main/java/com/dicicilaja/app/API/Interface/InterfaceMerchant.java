package com.dicicilaja.app.API.Interface;

import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemPartner.Partner;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by fawazrifqi on 15/04/18.
 */

public interface InterfaceMerchant {
    @Headers({
            "Accept: application/json",
    })
    @GET("profile/maxi/partner")
    Call<Partner> getPartner();
}
