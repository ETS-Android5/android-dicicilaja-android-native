package id.variable.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemUbahPassword.UbahPassword;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by fawazrifqi on 05/05/18.
 */

public interface InterfaceUbahPassword {
    @Headers({
            "Accept: application/json",
    })
    @POST("jodi/changepassword")
    @FormUrlEncoded
    Call<UbahPassword> change(@Header("Authorization") String apiKey,
                              @Field("old_password") String old_password,
                              @Field("new_password") String new_password,
                              @Field("second_new_password") String second_new_password);
}
