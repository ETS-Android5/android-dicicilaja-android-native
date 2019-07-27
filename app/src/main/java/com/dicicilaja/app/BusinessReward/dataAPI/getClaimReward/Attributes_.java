package com.dicicilaja.app.BusinessReward.dataAPI.getClaimReward;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes_ {
    @SerializedName("vendor_id")
    @Expose
    private Integer vendorId;
    @SerializedName("point")
    @Expose
    private Integer point;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("harga")
    @Expose
    private Integer harga;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("alt_foto")
    @Expose
    private String altFoto;
    @SerializedName("popularitas")
    @Expose
    private Integer popularitas;
    @SerializedName("used_by")
    @Expose
    private String usedBy;
    @SerializedName("profile_id")
    @Expose
    private Integer profileId;
    @SerializedName("testimoni")
    @Expose
    private String testimoni;
    @SerializedName("rating")
    @Expose
    private String rating;

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getAltFoto() {
        return altFoto;
    }

    public void setAltFoto(String altFoto) {
        this.altFoto = altFoto;
    }

    public Integer getPopularitas() {
        return popularitas;
    }

    public void setPopularitas(Integer popularitas) {
        this.popularitas = popularitas;
    }

    public String getUsedBy() {
        return usedBy;
    }

    public void setUsedBy(String usedBy) {
        this.usedBy = usedBy;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public String getTestimoni() {
        return testimoni;
    }

    public void setTestimoni(String testimoni) {
        this.testimoni = testimoni;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

}
