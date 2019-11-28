package com.dicicilaja.app.API.Model.AreaBankRequest.Distrik;


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

	@Override
 	public String toString(){
		return 
			"Relationships{" + 
			"provinsi = '" + provinsi + '\'' + 
			"}";
		}
}