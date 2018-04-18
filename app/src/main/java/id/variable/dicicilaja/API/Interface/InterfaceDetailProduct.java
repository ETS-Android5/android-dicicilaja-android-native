package id.variable.dicicilaja.API.Interface;

import id.variable.dicicilaja.API.Item.DetailProduct.DetailProduct;
import id.variable.dicicilaja.API.Item.Promo.Promo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by ziterz on 06/04/18.
 */

public interface InterfaceDetailProduct {
    @Headers({
            "Accept: application/json",
    })
    @GET("product/{id}")
    Call<DetailProduct> getDetailProduct(@Path("id") int id);
}
