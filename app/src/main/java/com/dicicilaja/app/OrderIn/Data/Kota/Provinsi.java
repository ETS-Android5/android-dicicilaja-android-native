package com.dicicilaja.app.OrderIn.Data.Kota;

import com.google.gson.annotations.SerializedName;

public class Provinsi{

	@SerializedName("data")
	private Data data;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}
}