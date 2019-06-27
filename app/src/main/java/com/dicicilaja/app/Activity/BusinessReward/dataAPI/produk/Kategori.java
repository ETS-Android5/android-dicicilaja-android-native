package com.dicicilaja.app.Activity.BusinessReward.dataAPI.produk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kategori {
    @SerializedName("data")
    @Expose
    private Data_ data;

    public Data_ getData() {
        return data;
    }

    public void setData(Data_ data) {
        this.data = data;
    }
}
