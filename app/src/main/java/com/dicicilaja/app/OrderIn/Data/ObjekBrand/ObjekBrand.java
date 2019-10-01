package com.dicicilaja.app.OrderIn.Data.ObjekBrand;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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