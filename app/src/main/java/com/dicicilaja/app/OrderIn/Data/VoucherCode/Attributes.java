package com.dicicilaja.app.OrderIn.Data.VoucherCode;

import com.google.gson.annotations.SerializedName;

public class Attributes{

	@SerializedName("status_id")
	private int statusId;

	@SerializedName("code")
	private String code;

	@SerializedName("nama")
	private String nama;

	@SerializedName("deskripsi")
	private String deskripsi;

	public void setStatusId(int statusId){
		this.statusId = statusId;
	}

	public int getStatusId(){
		return statusId;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

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
}