package com.dicicilaja.app.Activity.BusinessReward.dataAPI.detailProduk;

import com.dicicilaja.app.Activity.BusinessReward.dataAPI.kategori.Included;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example {
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("included")
    @Expose
    private List<Included> included = null;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public List<Included> getIncluded() {
        return included;
    }

    public void setIncluded(List<Included> included) {
        this.included = included;
    }
}
