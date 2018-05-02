package id.variable.dicicilaja.Activity.RemoteMarketplace.InterfaceAxiDetail;

import id.variable.dicicilaja.API.Item.Request.Request;
import id.variable.dicicilaja.Activity.RemoteMarketplace.ItemAxiDetail.AXIDetail;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by fawazrifqi on 29/04/18.
 */

public interface InterfaceAxiDetail {
    @Headers({
            "Accept: application/json",
    })
    @GET("/axi/detail")
    Call<AXIDetail> getDetail(@Header("Authorization") String apiKey);
}
