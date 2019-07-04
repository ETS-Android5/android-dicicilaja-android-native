package com.dicicilaja.app.BusinessReward.dataAPI.cabang;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes {
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("kode")
    @Expose
    private String kode;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("desa_id")
    @Expose
    private Object desaId;
    @SerializedName("area_id")
    @Expose
    private Integer areaId;
    @SerializedName("no_telp_1")
    @Expose
    private String noTelp1;
    @SerializedName("no_telp_2")
    @Expose
    private String noTelp2;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Object getDesaId() {
        return desaId;
    }

    public void setDesaId(Object desaId) {
        this.desaId = desaId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getNoTelp1() {
        return noTelp1;
    }

    public void setNoTelp1(String noTelp1) {
        this.noTelp1 = noTelp1;
    }

    public String getNoTelp2() {
        return noTelp2;
    }

    public void setNoTelp2(String noTelp2) {
        this.noTelp2 = noTelp2;
    }

}
