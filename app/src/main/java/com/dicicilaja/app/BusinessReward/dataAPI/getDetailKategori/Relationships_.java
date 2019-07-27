package com.dicicilaja.app.BusinessReward.dataAPI.getDetailKategori;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Relationships_ {
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("kategori")
    @Expose
    private Kategori kategori;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }


}
