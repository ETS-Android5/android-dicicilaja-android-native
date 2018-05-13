package id.variable.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemPromo.Promo;
import id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemRecommendation.Recommendation;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by fawazrifqi on 13/05/18.
 */

public interface InterfaceRecommendation {
    @Headers({
            "Accept: application/json",
    })
    @GET("jodi/recommendation")
    Call<Recommendation> getRecommend();
}
