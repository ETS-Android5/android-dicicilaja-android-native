package com.dicicilaja.app.OrderIn.Data.AreaSimulasi;

import com.google.gson.annotations.SerializedName;

public class Wilayah{

	@SerializedName("data")
	private Data data;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}
}