package com.dicicilaja.app.API.Model.AreaBankRequest.Desa;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class Desa{

	@SerializedName("data")
	private List<DataItem> data;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"Desa{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}