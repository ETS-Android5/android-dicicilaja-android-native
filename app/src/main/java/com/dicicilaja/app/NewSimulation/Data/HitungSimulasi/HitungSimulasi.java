package com.dicicilaja.app.NewSimulation.Data.HitungSimulasi;

import com.google.gson.annotations.SerializedName;

public class HitungSimulasi{

	@SerializedName("data")
	private Data data;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}
}