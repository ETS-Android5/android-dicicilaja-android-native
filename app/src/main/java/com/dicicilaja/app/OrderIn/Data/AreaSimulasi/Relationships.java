package com.dicicilaja.app.OrderIn.Data.AreaSimulasi;

import com.google.gson.annotations.SerializedName;

public class Relationships{

	@SerializedName("wilayah")
	private Wilayah wilayah;

	public void setWilayah(Wilayah wilayah){
		this.wilayah = wilayah;
	}

	public Wilayah getWilayah(){
		return wilayah;
	}
}