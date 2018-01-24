package id.variable.dicicilaja.Remote;

import id.variable.dicicilaja.Model.ResRequestProcess;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by LABMUL03 on 24/01/2018.
 */

public interface RequestProcess {

    @POST("assign")
    @FormUrlEncoded
    Call<ResRequestProcess> assign(@Field("transaction_id") String transaction_id,
                                   @Field("assigned_id") String assigned_id,
                                   @Field("notes") String notes);
}
