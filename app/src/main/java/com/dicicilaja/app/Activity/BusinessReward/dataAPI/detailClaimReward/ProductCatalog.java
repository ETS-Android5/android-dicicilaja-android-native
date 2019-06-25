package com.dicicilaja.app.Activity.BusinessReward.dataAPI.detailClaimReward;

import com.google.gson.annotations.SerializedName;

public class ProductCatalog{

	@SerializedName("created_at")
	private Object createdAt;

	@SerializedName("deleted_at")
	private Object deletedAt;

	@SerializedName("point")
	private int point;

	@SerializedName("ppn")
	private int ppn;

	@SerializedName("status_id")
	private int statusId;

	@SerializedName("nama")
	private String nama;

	@SerializedName("harga")
	private int harga;

	@SerializedName("foto")
	private String foto;

	@SerializedName("updated_at")
	private Object updatedAt;

	@SerializedName("alt_foto")
	private String altFoto;

	@SerializedName("vendor_id")
	private int vendorId;

	@SerializedName("id")
	private int id;

	@SerializedName("deskripsi")
	private String deskripsi;

	public void setCreatedAt(Object createdAt){
		this.createdAt = createdAt;
	}

	public Object getCreatedAt(){
		return createdAt;
	}

	public void setDeletedAt(Object deletedAt){
		this.deletedAt = deletedAt;
	}

	public Object getDeletedAt(){
		return deletedAt;
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

	public void setUpdatedAt(Object updatedAt){
		this.updatedAt = updatedAt;
	}

	public Object getUpdatedAt(){
		return updatedAt;
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

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setDeskripsi(String deskripsi){
		this.deskripsi = deskripsi;
	}

	public String getDeskripsi(){
		return deskripsi;
	}
}