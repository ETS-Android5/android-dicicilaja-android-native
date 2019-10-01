package com.dicicilaja.app.OrderIn.Data.Kota;

import com.google.gson.annotations.SerializedName;

public class Relationships{

	@SerializedName("provinsi")
	private Provinsi provinsi;

	public void setProvinsi(Provinsi provinsi){
		this.provinsi = provinsi;
	}

	public Provinsi getProvinsi(){
		return provinsi;
	}
}