package id.variable.orderinapp.Remote;

import id.variable.orderinapp.Model.ResObj;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ziterz on 11/29/2017.
 */

public interface UserService {

    @GET("login/{username}/{password}")
    Call<ResObj> login(@Path("username") String username, @Path("password") String password);

}
