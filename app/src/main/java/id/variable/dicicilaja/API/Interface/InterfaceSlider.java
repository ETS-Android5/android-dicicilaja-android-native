package id.variable.dicicilaja.API.Interface;

import id.variable.dicicilaja.API.Item.Slider.Slider;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by fawazrifqi on 16/04/18.
 */

public interface InterfaceSlider {
    @Headers({
            "Accept: application/json",
    })
    @GET("/featured-image")
    Call<Slider> getSlider();
}

