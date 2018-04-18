package id.variable.dicicilaja.API.Interface;

import id.variable.dicicilaja.API.Item.Mechant.Merchant;
import id.variable.dicicilaja.API.Item.Promo.Promo;
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
    @GET("/merchant")
    Call<Merchant> getMerchant();
}
