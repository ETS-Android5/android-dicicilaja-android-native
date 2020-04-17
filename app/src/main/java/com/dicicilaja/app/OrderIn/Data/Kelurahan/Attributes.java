package com.dicicilaja.app.OrderIn.Data.Kelurahan;

import com.google.gson.annotations.SerializedName;

public class Attributes{

	@SerializedName("nama")
	private String nama;

	@SerializedName("updated-at")
	private String updatedAt;

	@SerializedName("kode_pos")
	private String kodePos;

	@SerializedName("created-at")
	private String createdAt;

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setKodePos(String kodePos){
		this.kodePos = kodePos;
	}

	public String getKodePos(){
		return kodePos;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}
}