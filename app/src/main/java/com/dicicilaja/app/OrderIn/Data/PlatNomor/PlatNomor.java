package com.dicicilaja.app.OrderIn.Data.PlatNomor;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PlatNomor{

	@SerializedName("data")
	private List<DataItem> data;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}
}