package id.variable.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemMaxiSehat.MaxiSehat;
import id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemMaxiTravel.MaxiTravel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by fawazrifqi on 13/05/18.
 */

public interface InterfaceMaxiSehat {
    @Headers({
            "Accept: application/json",
    })
    @GET("jodi/maxi/sehat")
    Call<MaxiSehat> getProduct(@Header("Authorization") String apiKey);
}
