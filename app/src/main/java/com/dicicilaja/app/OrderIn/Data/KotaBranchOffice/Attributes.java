package com.dicicilaja.app.OrderIn.Data.KotaBranchOffice;

import com.google.gson.annotations.SerializedName;

public class Attributes{

	@SerializedName("kota")
	private String kota;

	public void setKota(String kota){
		this.kota = kota;
	}

	public String getKota(){
		return kota;
	}
}