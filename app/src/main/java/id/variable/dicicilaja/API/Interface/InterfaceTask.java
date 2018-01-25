package id.variable.dicicilaja.API.Interface;

import id.variable.dicicilaja.API.Item.Task.Task;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by ziterz on 1/25/2018.
 */

public interface InterfaceTask {

    @Headers({
            "Accept: application/json",
    })
    @GET("task")
    Call<Task> getTask(@Header("Authorization") String apiKey);
}
