package com.dicicilaja.app.OrderIn.Data.AreaBranchOffice;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AreaBranchOffice{

	@SerializedName("data")
	private List<DataItem> data;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}
}