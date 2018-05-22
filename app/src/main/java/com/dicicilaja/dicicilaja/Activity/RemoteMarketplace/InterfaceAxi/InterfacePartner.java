package com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemPartner.Partner;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by fawazrifqi on 13/05/18.
 */

public interface InterfacePartner {
    @Headers({
            "Accept: application/json",
    })
    @GET("jodi/partner")
    Call<Partner> getPartner();
}
