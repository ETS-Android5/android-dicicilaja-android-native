package com.dicicilaja.app.OrderIn.Data;

import com.google.gson.annotations.SerializedName;

public class Attributes{

	@SerializedName("nama")
	private String nama;

	@SerializedName("updated-at")
	private String updatedAt;

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

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}
}