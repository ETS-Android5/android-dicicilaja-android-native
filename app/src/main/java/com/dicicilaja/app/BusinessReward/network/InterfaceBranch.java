package com.dicicilaja.app.BusinessReward.network;

import com.dicicilaja.app.BusinessReward.dataAPI.area.Area;
import com.dicicilaja.app.BusinessReward.dataAPI.branch.Branch;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceBranch {
    @GET("branch")
    Call<Branch> getCabang();

    @GET("area")
    Call<Area> getArea();
}
