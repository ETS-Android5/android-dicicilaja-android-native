package com.dicicilaja.app.OrderIn.Data.CabangLainnya;

import com.google.gson.annotations.SerializedName;

public class Attributes{

	@SerializedName("nama")
	private String nama;

	@SerializedName("kode")
	private String kode;

	@SerializedName("updated-at")
	private String updatedAt;

	@SerializedName("no_telp_2")
	private String noTelp2;

	@SerializedName("created-at")
	private String createdAt;

	@SerializedName("no_telp_1")
	private String noTelp1;

	@SerializedName("alamat")
	private String alamat;

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setKode(String kode){
		this.kode = kode;
	}

	public String getKode(){
		return kode;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setNoTelp2(String noTelp2){
		this.noTelp2 = noTelp2;
	}

	public String getNoTelp2(){
		return noTelp2;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setNoTelp1(String noTelp1){
		this.noTelp1 = noTelp1;
	}

	public String getNoTelp1(){
		return noTelp1;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}
}