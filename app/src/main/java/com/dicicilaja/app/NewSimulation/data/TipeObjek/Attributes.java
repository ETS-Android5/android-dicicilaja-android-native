package com.dicicilaja.app.NewSimulation.data.TipeObjek;

import com.google.gson.annotations.SerializedName;

public class Attributes{

	@SerializedName("nama")
	private String nama;

	public Attributes(String nama) {
		this.nama = nama;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}
}