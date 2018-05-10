package id.variable.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemAxiDetail.AXIDetail;
import id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemDetailProgramMaxi.DetailProgramMaxi;
import id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemRekanBisnis.RekanBisnis;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by fawazrifqi on 10/05/18.
 */

public interface InterfaceDetailProgramMaxi {
    @Headers({
            "Accept: application/json",
    })
    @GET("jodi/program/maxi/{id}")
    Call<DetailProgramMaxi> getDetail(@Header("Authorization") String apiKey, @Path("id") String id);
}
