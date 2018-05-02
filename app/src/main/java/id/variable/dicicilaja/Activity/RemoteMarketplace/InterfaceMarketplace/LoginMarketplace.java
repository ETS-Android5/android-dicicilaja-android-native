package id.variable.dicicilaja.Activity.RemoteMarketplace.InterfaceMarketplace;

import id.variable.dicicilaja.Activity.RemoteMarketplace.ItemMarketplace.LoginObj;
import id.variable.dicicilaja.Model.ResObj;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by fawazrifqi on 29/04/18.
 */

public interface LoginMarketplace {

    @POST("login")
    @FormUrlEncoded
    Call<LoginObj> login(@Field("username") String username,
                         @Field("password") String password);
}
