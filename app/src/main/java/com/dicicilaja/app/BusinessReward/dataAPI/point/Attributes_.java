package com.dicicilaja.app.BusinessReward.dataAPI.point;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes_ {
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("tahun")
    @Expose
    private Integer tahun;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getTahun() {
        return tahun;
    }

    public void setTahun(Integer tahun) {
        this.tahun = tahun;
    }
}
