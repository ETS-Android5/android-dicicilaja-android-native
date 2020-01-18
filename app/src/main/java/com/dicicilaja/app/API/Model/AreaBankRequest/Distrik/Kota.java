package com.dicicilaja.app.API.Model.AreaBankRequest.Distrik;


import com.google.gson.annotations.SerializedName;


public class Kota{

	@SerializedName("data")
	private Data data;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"Kota{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}