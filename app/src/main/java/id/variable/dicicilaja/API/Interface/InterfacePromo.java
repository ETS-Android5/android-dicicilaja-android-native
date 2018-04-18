package id.variable.dicicilaja.API.Interface;

import id.variable.dicicilaja.API.Item.Promo.Promo;
import id.variable.dicicilaja.API.Item.Task.Task;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by ziterz on 05/04/18.
 */

public interface InterfacePromo {
    @Headers({
            "Accept: application/json",
    })
    @GET("/product/promo")
    Call<Promo> getPromo();
}
