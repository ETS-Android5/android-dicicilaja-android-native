package com.dicicilaja.app.API.Model.AreaBankRequest.Desa;


import com.google.gson.annotations.SerializedName;


public class Distrik{

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
			"Distrik{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}