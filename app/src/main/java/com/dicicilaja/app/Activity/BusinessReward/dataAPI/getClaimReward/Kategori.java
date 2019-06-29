package com.dicicilaja.app.Activity.BusinessReward.dataAPI.getClaimReward;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kategori {
    @SerializedName("data")
    @Expose
    private Data___ data;

    public Data___ getData() {
        return data;
    }

    public void setData(Data___ data) {
        this.data = data;
    }
}
