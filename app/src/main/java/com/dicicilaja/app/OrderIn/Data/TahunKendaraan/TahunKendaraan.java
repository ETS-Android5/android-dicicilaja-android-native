package com.dicicilaja.app.OrderIn.Data.TahunKendaraan;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TahunKendaraan{

	@SerializedName("data")
	private List<DataItem> data;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}
}