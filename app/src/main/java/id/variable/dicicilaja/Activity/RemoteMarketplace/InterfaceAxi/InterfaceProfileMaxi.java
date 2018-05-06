package id.variable.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemProfileAxi.ProfileAxi;
import id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemProfileMaxi.ProfileMaxi;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by fawazrifqi on 06/05/18.
 */

public interface InterfaceProfileMaxi {
    @Headers({
            "Accept: application/json",
    })
    @GET("jodi/profilemaxi")
    Call<ProfileMaxi> getProfile(@Header("Authorization") String apiKey);
}
