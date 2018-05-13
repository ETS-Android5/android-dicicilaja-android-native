package id.variable.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemMaxiTravel.MaxiTravel;
import id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemProfileAxi.ProfileAxi;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by fawazrifqi on 13/05/18.
 */

public interface InterfaceMaxiTravel {
    @Headers({
            "Accept: application/json",
    })
    @GET("jodi/maxi/travel")
    Call<MaxiTravel> getProduct(@Header("Authorization") String apiKey);
}
