package com.dicicilaja.app.Activity.BusinessReward.dataAPI.detailProduk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes{

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
	@SerializedName("ppn")
	@Expose
	private Integer ppn;
	@SerializedName("foto")
	@Expose
	private String foto;
	@SerializedName("alt_foto")
	@Expose
	private String altFoto;
	@SerializedName("popularitas")
	@Expose
	private Integer popularitas;

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

	public Integer getPpn() {
		return ppn;
	}

	public void setPpn(Integer ppn) {
		this.ppn = ppn;
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
}