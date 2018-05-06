package id.variable.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemInfoJaringan.InfoJaringan;
import id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemRekanBisnis.RekanBisnis;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by fawazrifqi on 06/05/18.
 */

public interface InterfaceRekanBisnis {
    @Headers({
            "Accept: application/json",
    })
    @GET("/jodi/rekanbisnis/{id}")
    Call<RekanBisnis> getRekanBisnis(@Header("Authorization") String apiKey,@Path("id") String id);
}
