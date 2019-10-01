package com.dicicilaja.app.OrderIn.Data.Kecamatan;

import com.google.gson.annotations.SerializedName;

public class Relationships{

	@SerializedName("kota")
	private Kota kota;

	public void setKota(Kota kota){
		this.kota = kota;
	}

	public Kota getKota(){
		return kota;
	}
}