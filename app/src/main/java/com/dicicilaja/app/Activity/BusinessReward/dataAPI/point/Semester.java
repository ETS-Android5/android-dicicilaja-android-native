package com.dicicilaja.app.Activity.BusinessReward.dataAPI.point;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Semester {
    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
