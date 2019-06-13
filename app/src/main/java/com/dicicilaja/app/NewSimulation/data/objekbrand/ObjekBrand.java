package com.dicicilaja.app.NewSimulation.data.objekbrand;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ObjekBrand {

	@SerializedName("data")
	private List<DataItem> data;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}
}