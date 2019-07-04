package com.dicicilaja.app.BusinessReward.dataAPI.area2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Relationships {
    @SerializedName("wilayah")
    @Expose
    private Wilayah wilayah;

    public Wilayah getWilayah() {
        return wilayah;
    }

    public void setWilayah(Wilayah wilayah) {
        this.wilayah = wilayah;
    }
}
