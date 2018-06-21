package com.dicicilaja.app.API.Interface;

import com.dicicilaja.app.API.Item.SliderDetail.SliderDetail;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by fawazrifqi on 16/04/18.
 */

public interface InterfaceSliderDetail {
    @Headers({
            "Accept: application/json",
    })
    @GET("featured-image/{id}")
    Call<SliderDetail> getSliderDetail(@Path("id") int id);
}
