package com.dicicilaja.app.API.Model.AreaBankRequest.Kota;


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

	@Override
 	public String toString(){
		return 
			"Attributes{" + 
			"nama = '" + nama + '\'' + 
			"}";
		}
}