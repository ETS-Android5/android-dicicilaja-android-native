package com.dicicilaja.app.Activity.BusinessReward.dataAPI.getClaimReward;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes {
    @SerializedName("profile_id")
    @Expose
    private String profileId;
    @SerializedName("nama_axi")
    @Expose
    private String namaAxi;
    @SerializedName("cabang_id")
    @Expose
    private Integer cabangId;
    @SerializedName("nama_cabang")
    @Expose
    private String namaCabang;
    @SerializedName("area_id")
    @Expose
    private Integer areaId;
    @SerializedName("nama_area")
    @Expose
    private String namaArea;
    @SerializedName("crh_id")
    @Expose
    private Integer crhId;
    @SerializedName("penerima")
    @Expose
    private String penerima;
    @SerializedName("product_catalog_id")
    @Expose
    private Integer productCatalogId;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("no_po")
    @Expose
    private String noPo;
    @SerializedName("ekspedisi")
    @Expose
    private String ekspedisi;
    @SerializedName("no_resi")
    @Expose
    private String noResi;
    @SerializedName("ongkos_kirim")
    @Expose
    private Integer ongkosKirim;
    @SerializedName("harga_barang_ongkir")
    @Expose
    private Integer hargaBarangOngkir;
    @SerializedName("ppn")
    @Expose
    private Integer ppn;
    @SerializedName("total_harga")
    @Expose
    private Integer totalHarga;
    @SerializedName("status_id")
    @Expose
    private Integer statusId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getNamaAxi() {
        return namaAxi;
    }

    public void setNamaAxi(String namaAxi) {
        this.namaAxi = namaAxi;
    }

    public Integer getCabangId() {
        return cabangId;
    }

    public void setCabangId(Integer cabangId) {
        this.cabangId = cabangId;
    }

    public String getNamaCabang() {
        return namaCabang;
    }

    public void setNamaCabang(String namaCabang) {
        this.namaCabang = namaCabang;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getNamaArea() {
        return namaArea;
    }

    public void setNamaArea(String namaArea) {
        this.namaArea = namaArea;
    }

    public Integer getCrhId() {
        return crhId;
    }

    public void setCrhId(Integer crhId) {
        this.crhId = crhId;
    }

    public String getPenerima() {
        return penerima;
    }

    public void setPenerima(String penerima) {
        this.penerima = penerima;
    }

    public Integer getProductCatalogId() {
        return productCatalogId;
    }

    public void setProductCatalogId(Integer productCatalogId) {
        this.productCatalogId = productCatalogId;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoPo() {
        return noPo;
    }

    public void setNoPo(String noPo) {
        this.noPo = noPo;
    }

    public String getEkspedisi() {
        return ekspedisi;
    }

    public void setEkspedisi(String ekspedisi) {
        this.ekspedisi = ekspedisi;
    }

    public String getNoResi() {
        return noResi;
    }

    public void setNoResi(String noResi) {
        this.noResi = noResi;
    }

    public Integer getOngkosKirim() {
        return ongkosKirim;
    }

    public void setOngkosKirim(Integer ongkosKirim) {
        this.ongkosKirim = ongkosKirim;
    }

    public Integer getHargaBarangOngkir() {
        return hargaBarangOngkir;
    }

    public void setHargaBarangOngkir(Integer hargaBarangOngkir) {
        this.hargaBarangOngkir = hargaBarangOngkir;
    }

    public Integer getPpn() {
        return ppn;
    }

    public void setPpn(Integer ppn) {
        this.ppn = ppn;
    }

    public Integer getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(Integer totalHarga) {
        this.totalHarga = totalHarga;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
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
