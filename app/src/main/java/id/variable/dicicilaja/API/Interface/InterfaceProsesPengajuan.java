package id.variable.dicicilaja.API.Interface;

import id.variable.dicicilaja.Model.ResObj;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ziterz on 23/01/2018.
 */

public interface InterfaceProsesPengajuan {
    @POST("assign")
    @FormUrlEncoded
    Call<ResObj> login(@Field("username") String username,
                       @Field("password") String password);
}
