package com.dicicilaja.app.BusinessReward.dataAPI.detailClaimReward;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes{

	@SerializedName("profile_id")
	@Expose
	private String profileId;
	@SerializedName("transaksi_id")
	@Expose
	private String transaksiId;
	@SerializedName("nama_axi")
	@Expose
	private String namaAxi;
	@SerializedName("cabang_id")
	@Expose
	private String cabangId;
	@SerializedName("nama_cabang")
	@Expose
	private String namaCabang;
	@SerializedName("area_id")
	@Expose
	private String areaId;
	@SerializedName("nama_area")
	@Expose
	private String namaArea;
	@SerializedName("crh_id")
	@Expose
	private String crhId;
	@SerializedName("penerima")
	@Expose
	private String penerima;
	@SerializedName("product_catalog_id")
	@Expose
	private String productCatalogId;
	@SerializedName("alamat")
	@Expose
	private Object alamat;
	@SerializedName("no_po")
	@Expose
	private Object noPo;
	@SerializedName("ktp_npwp")
	@Expose
	private String ktpNpwp;
	@SerializedName("ekspedisi")
	@Expose
	private Object ekspedisi;
	@SerializedName("no_resi")
	@Expose
	private Object noResi;
	@SerializedName("ongkos_kirim")
	@Expose
	private Object ongkosKirim;
	@SerializedName("harga_barang_ongkir")
	@Expose
	private Object hargaBarangOngkir;
	@SerializedName("ppn")
	@Expose
	private Object ppn;
	@SerializedName("total_harga")
	@Expose
	private Object totalHarga;
	@SerializedName("status_id")
	@Expose
	private String statusId;
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

	public String getCabangId() {
		return cabangId;
	}

	public void setCabangId(String cabangId) {
		this.cabangId = cabangId;
	}

	public String getNamaCabang() {
		return namaCabang;
	}

	public void setNamaCabang(String namaCabang) {
		this.namaCabang = namaCabang;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getNamaArea() {
		return namaArea;
	}

	public void setNamaArea(String namaArea) {
		this.namaArea = namaArea;
	}

	public String getCrhId() {
		return crhId;
	}

	public void setCrhId(String crhId) {
		this.crhId = crhId;
	}

	public String getPenerima() {
		return penerima;
	}

	public void setPenerima(String penerima) {
		this.penerima = penerima;
	}

	public String getProductCatalogId() {
		return productCatalogId;
	}

	public void setProductCatalogId(String productCatalogId) {
		this.productCatalogId = productCatalogId;
	}

	public Object getAlamat() {
		return alamat;
	}

	public void setAlamat(Object alamat) {
		this.alamat = alamat;
	}

	public Object getNoPo() {
		return noPo;
	}

	public void setNoPo(Object noPo) {
		this.noPo = noPo;
	}

	public String getKtpNpwp() {
		return ktpNpwp;
	}

	public void setKtpNpwp(String ktpNpwp) {
		this.ktpNpwp = ktpNpwp;
	}

	public Object getEkspedisi() {
		return ekspedisi;
	}

	public void setEkspedisi(Object ekspedisi) {
		this.ekspedisi = ekspedisi;
	}

	public Object getNoResi() {
		return noResi;
	}

	public void setNoResi(Object noResi) {
		this.noResi = noResi;
	}

	public Object getOngkosKirim() {
		return ongkosKirim;
	}

	public void setOngkosKirim(Object ongkosKirim) {
		this.ongkosKirim = ongkosKirim;
	}

	public Object getHargaBarangOngkir() {
		return hargaBarangOngkir;
	}

	public void setHargaBarangOngkir(Object hargaBarangOngkir) {
		this.hargaBarangOngkir = hargaBarangOngkir;
	}

	public Object getPpn() {
		return ppn;
	}

	public void setPpn(Object ppn) {
		this.ppn = ppn;
	}

	public Object getTotalHarga() {
		return totalHarga;
	}

	public void setTotalHarga(Object totalHarga) {
		this.totalHarga = totalHarga;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
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

	public String getTransaksiId() {
		return transaksiId;
	}

	public void setTransaksiId(String transaksiId) {
		this.transaksiId = transaksiId;
	}

}