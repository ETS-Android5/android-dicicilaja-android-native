
package com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemCreateOrder.Branch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes {

    @SerializedName("kode")
    @Expose
    private String kode;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("desa")
    @Expose
    private String desa;
    @SerializedName("distrik")
    @Expose
    private String distrik;
    @SerializedName("kota")
    @Expose
    private String kota;
    @SerializedName("provinsi")
    @Expose
    private String provinsi;
    @SerializedName("no_telp_1")
    @Expose
    private String noTelp1;
    @SerializedName("no_telp_2")
    @Expose
    private Object noTelp2;
    @SerializedName("created-at")
    @Expose
    private String createdAt;
    @SerializedName("updated-at")
    @Expose
    private String updatedAt;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDesa() {
        return desa;
    }

    public void setDesa(String desa) {
        this.desa = desa;
    }

    public String getDistrik() {
        return distrik;
    }

    public void setDistrik(String distrik) {
        this.distrik = distrik;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getNoTelp1() {
        return noTelp1;
    }

    public void setNoTelp1(String noTelp1) {
        this.noTelp1 = noTelp1;
    }

    public Object getNoTelp2() {
        return noTelp2;
    }

    public void setNoTelp2(Object noTelp2) {
        this.noTelp2 = noTelp2;
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

}
