
package com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemSlider;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes {

    @SerializedName("gambar")
    @Expose
    private String gambar;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("gambar_alternatif")
    @Expose
    private String gambarAlternatif;
    @SerializedName("tipe")
    @Expose
    private String tipe;
    @SerializedName("posisi")
    @Expose
    private Integer posisi;
    @SerializedName("created-at")
    @Expose
    private String createdAt;
    @SerializedName("updated-at")
    @Expose
    private String updatedAt;

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getGambarAlternatif() {
        return gambarAlternatif;
    }

    public void setGambarAlternatif(String gambarAlternatif) {
        this.gambarAlternatif = gambarAlternatif;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public Integer getPosisi() {
        return posisi;
    }

    public void setPosisi(Integer posisi) {
        this.posisi = posisi;
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
