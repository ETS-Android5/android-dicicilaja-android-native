package com.dicicilaja.app.BusinessReward.dataAPI.detailStatus;

import com.google.gson.annotations.SerializedName;

public class Attributes{

	@SerializedName("nama")
	private String nama;

	@SerializedName("deskripsi")
	private String deskripsi;

	@SerializedName("used_by")
	private String usedBy;

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setDeskripsi(String deskripsi){
		this.deskripsi = deskripsi;
	}

	public String getDeskripsi(){
		return deskripsi;
	}

	public void setUsedBy(String usedBy){
		this.usedBy = usedBy;
	}

	public String getUsedBy(){
		return usedBy;
	}
}