package com.dicicilaja.app.OrderIn.Data.ObjekModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ObjekModel{

	@SerializedName("data")
	private List<DataItem> data;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}
}