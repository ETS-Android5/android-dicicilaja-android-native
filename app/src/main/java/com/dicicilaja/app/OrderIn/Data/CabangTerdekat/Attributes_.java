
package com.dicicilaja.app.OrderIn.Data.CabangTerdekat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes_ {

    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("created-at")
    @Expose
    private String createdAt;
    @SerializedName("updated-at")
    @Expose
    private String updatedAt;
    @SerializedName("kode_pos")
    @Expose
    private String kodePos;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getKodePos() {
        return kodePos;
    }

    public void setKodePos(String kodePos) {
        this.kodePos = kodePos;
    }

}
