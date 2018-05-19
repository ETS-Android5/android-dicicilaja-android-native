package id.variable.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemRecommendation.Recommendation;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by fawazrifqi on 16/05/18.
 */

public interface InterfacePengajuanMarketplace {
    @Headers({
            "Accept: application/json",
    })
    @GET("jodi/recommendation")
    Call<id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemRequestMarketplace.Recommendation> getRecommend();
}
