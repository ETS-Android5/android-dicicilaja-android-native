package com.dicicilaja.app.OrderIn.Data.AreaSimulasi;

import com.google.gson.annotations.SerializedName;

public class Attributes{

	@SerializedName("nama")
	private String nama;

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}
}