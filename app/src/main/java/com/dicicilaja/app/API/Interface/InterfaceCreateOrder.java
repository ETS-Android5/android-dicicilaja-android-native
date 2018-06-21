package com.dicicilaja.app.API.Interface;

import com.dicicilaja.app.API.Item.CreateOrder.Colleteral.Colleteral;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by fawazrifqi on 21/04/18.
 */

public interface InterfaceCreateOrder {

    @GET("request/colleteral")
    Call<Colleteral> getColleteral();


}
