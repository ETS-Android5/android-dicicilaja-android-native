package com.dicicilaja.app.BusinessReward.dataAPI.cabang;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Relationships_ {
    @SerializedName("wilayah")
    @Expose
    private Object wilayah;

    public Object getWilayah() {
        return wilayah;
    }

    public void setWilayah(Object wilayah) {
        this.wilayah = wilayah;
    }

}
