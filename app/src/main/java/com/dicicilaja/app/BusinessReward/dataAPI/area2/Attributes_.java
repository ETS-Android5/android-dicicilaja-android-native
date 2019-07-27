package com.dicicilaja.app.BusinessReward.dataAPI.area2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes_ {
    @SerializedName("nama")
    @Expose
    private String nama;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }



}
