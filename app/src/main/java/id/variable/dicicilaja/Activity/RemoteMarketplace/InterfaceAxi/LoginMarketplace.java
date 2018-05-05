package id.variable.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemMarketplace.LoginObj;
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
