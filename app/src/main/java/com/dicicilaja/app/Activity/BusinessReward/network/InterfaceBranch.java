package com.dicicilaja.app.Activity.BusinessReward.network;

import com.dicicilaja.app.Activity.BusinessReward.dataAPI.area.Area;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.branch.Branch;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceBranch {
    @GET("branch")
    Call<Branch> getCabang();

    @GET("area")
    Call<Area> getArea();
}
