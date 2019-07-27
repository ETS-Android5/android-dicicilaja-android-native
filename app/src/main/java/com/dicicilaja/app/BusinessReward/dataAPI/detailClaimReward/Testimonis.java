package com.dicicilaja.app.BusinessReward.dataAPI.detailClaimReward;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Testimonis {
    @SerializedName("data")
    @Expose
    private List<Object> data;

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
}
