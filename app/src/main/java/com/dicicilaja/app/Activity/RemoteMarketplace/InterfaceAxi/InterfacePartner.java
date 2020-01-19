package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemPartner.Partner;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by fawazrifqi on 13/05/18.
 */

public interface InterfacePartner {
    @Headers({
            "Accept: application/json",
    })
    @GET("partner/")
    Call<Partner> getPartner();
}
