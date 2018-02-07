package id.variable.dicicilaja.Remote;

import id.variable.dicicilaja.Model.ResObj;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ziterz on 2/7/2018.
 */

public interface UserFirebase {

    @POST("login")
    @FormUrlEncoded
    Call<ResObj> login_token(@Field("username") String username,
                             @Field("password") String password,
                             @Field("firebase_token") String firebase_token);
}
