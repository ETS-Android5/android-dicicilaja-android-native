package com.dicicilaja.app.Activity.BusinessReward.dataAPI.getClaimReward;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClaimReward {
    @SerializedName("data")
    @Expose
    private Data____ data;

    public Data____ getData() {
        return data;
    }

    public void setData(Data____ data) {
        this.data = data;
    }

}
