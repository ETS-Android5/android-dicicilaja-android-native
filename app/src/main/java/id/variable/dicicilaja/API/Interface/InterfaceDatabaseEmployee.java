package id.variable.dicicilaja.API.Interface;

import id.variable.dicicilaja.API.Item.DatabaseEmployeeResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by ziterz on 1/9/2018.
 */

public interface InterfaceDatabaseEmployee {

    @Headers({
            "Accept: application/json",
    })
    @GET("user/crh")
    Call<DatabaseEmployeeResponse> getDatabaseEmployee(@Header("Authorization") String apiKey);

}