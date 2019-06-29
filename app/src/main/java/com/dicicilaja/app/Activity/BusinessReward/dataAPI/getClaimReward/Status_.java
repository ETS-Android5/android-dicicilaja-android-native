package com.dicicilaja.app.Activity.BusinessReward.dataAPI.getClaimReward;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status_ {
    @SerializedName("data")
    @Expose
    private Data__ data;

    public Data__ getData() {
        return data;
    }

    public void setData(Data__ data) {
        this.data = data;
    }
}
