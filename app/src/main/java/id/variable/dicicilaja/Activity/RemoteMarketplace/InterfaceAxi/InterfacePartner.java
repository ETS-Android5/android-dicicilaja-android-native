package id.variable.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemPartner.Partner;
import id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemPromo.Promo;
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
