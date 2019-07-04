package com.dicicilaja.app.BusinessReward.dataAPI.area2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes {
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("is_area_simulasi")
    @Expose
    private Boolean isAreaSimulasi;
    @SerializedName("wilayah_id")
    @Expose
    private Integer wilayahId;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Boolean getIsAreaSimulasi() {
        return isAreaSimulasi;
    }

    public void setIsAreaSimulasi(Boolean isAreaSimulasi) {
        this.isAreaSimulasi = isAreaSimulasi;
    }

    public Integer getWilayahId() {
        return wilayahId;
    }

    public void setWilayahId(Integer wilayahId) {
        this.wilayahId = wilayahId;
    }

}
