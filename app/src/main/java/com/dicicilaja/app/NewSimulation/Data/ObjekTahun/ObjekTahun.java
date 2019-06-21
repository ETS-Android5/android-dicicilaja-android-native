package com.dicicilaja.app.NewSimulation.Data.ObjekTahun;

import com.google.gson.annotations.SerializedName;

public class ObjekTahun{

	@SerializedName("data")
	private Data data;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public ObjekTahun(Data data) {
		this.data = data;
	}
}