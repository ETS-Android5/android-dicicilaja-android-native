package com.dicicilaja.app.Activity.BusinessReward.dataAPI.produk;

import com.google.gson.annotations.SerializedName;

public class Attributes{

	@SerializedName("status_id")
	private int statusId;

	@SerializedName("nama")
	private String nama;

	@SerializedName("harga")
	private int harga;

	@SerializedName("foto")
	private String foto;

	@SerializedName("alt_foto")
	private String altFoto;

	@SerializedName("vendor_id")
	private int vendorId;

	@SerializedName("deskripsi")
	private String deskripsi;

	@SerializedName("point")
	private int point;

	@SerializedName("ppn")
	private int ppn;

	public void setStatusId(int statusId){
		this.statusId = statusId;
	}

	public int getStatusId(){
		return statusId;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setHarga(int harga){
		this.harga = harga;
	}

	public int getHarga(){
		return harga;
	}

	public void setFoto(String foto){
		this.foto = foto;
	}

	public String getFoto(){
		return foto;
	}

	public void setAltFoto(String altFoto){
		this.altFoto = altFoto;
	}

	public String getAltFoto(){
		return altFoto;
	}

	public void setVendorId(int vendorId){
		this.vendorId = vendorId;
	}

	public int getVendorId(){
		return vendorId;
	}

	public void setDeskripsi(String deskripsi){
		this.deskripsi = deskripsi;
	}

	public String getDeskripsi(){
		return deskripsi;
	}

	public void setPoint(int point){
		this.point = point;
	}

	public int getPoint(){
		return point;
	}

	public void setPpn(int ppn){
		this.ppn = ppn;
	}

	public int getPpn(){
		return ppn;
	}
}