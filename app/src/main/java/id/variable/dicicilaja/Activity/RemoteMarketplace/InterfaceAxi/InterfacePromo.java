package id.variable.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemProfileMaxi.ProfileMaxi;
import id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemPromo.Promo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by fawazrifqi on 13/05/18.
 */

public interface InterfacePromo {
    @Headers({
            "Accept: application/json",
    })
    @GET("jodi/promo")
    Call<Promo> getPromo();
}
