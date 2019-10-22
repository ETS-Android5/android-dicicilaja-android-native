
package com.dicicilaja.app.OrderIn.Data.Axi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes {

    @SerializedName("profile_id")
    @Expose
    private Integer profileId;
    @SerializedName("status_id")
    @Expose
    private Integer statusId;
    @SerializedName("npwp_id")
    @Expose
    private Integer npwpId;
    @SerializedName("nomor_axi_id")
    @Expose
    private String nomorAxiId;
    @SerializedName("axi_id_reff")
    @Expose
    private Integer axiIdReff;
    @SerializedName("cabang_id")
    @Expose
    private Integer cabangId;
    @SerializedName("tanggal_daftar")
    @Expose
    private String tanggalDaftar;
    @SerializedName("cabang_daftar")
    @Expose
    private Integer cabangDaftar;
    @SerializedName("nomor_tagihan")
    @Expose
    private String nomorTagihan;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("created-at")
    @Expose
    private String createdAt;
    @SerializedName("updated-at")
    @Expose
    private String updatedAt;

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getNpwpId() {
        return npwpId;
    }

    public void setNpwpId(Integer npwpId) {
        this.npwpId = npwpId;
    }

    public String getNomorAxiId() {
        return nomorAxiId;
    }

    public void setNomorAxiId(String nomorAxiId) {
        this.nomorAxiId = nomorAxiId;
    }

    public Integer getAxiIdReff() {
        return axiIdReff;
    }

    public void setAxiIdReff(Integer axiIdReff) {
        this.axiIdReff = axiIdReff;
    }

    public Integer getCabangId() {
        return cabangId;
    }

    public void setCabangId(Integer cabangId) {
        this.cabangId = cabangId;
    }

    public String getTanggalDaftar() {
        return tanggalDaftar;
    }

    public void setTanggalDaftar(String tanggalDaftar) {
        this.tanggalDaftar = tanggalDaftar;
    }

    public Integer getCabangDaftar() {
        return cabangDaftar;
    }

    public void setCabangDaftar(Integer cabangDaftar) {
        this.cabangDaftar = cabangDaftar;
    }

    public String getNomorTagihan() {
        return nomorTagihan;
    }

    public void setNomorTagihan(String nomorTagihan) {
        this.nomorTagihan = nomorTagihan;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
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
