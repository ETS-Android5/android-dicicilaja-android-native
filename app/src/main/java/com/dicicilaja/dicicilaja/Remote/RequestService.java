package com.dicicilaja.dicicilaja.Remote;

/**
 * Created by Rienaldi on 09/01/2018.
 */

import java.util.List;

import com.dicicilaja.dicicilaja.Model.Request;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestService {

    @GET("request")
    Call<List<Request>> getRequest();

}
