package com.dicicilaja.app.API.Model.AreaBankRequest.Desa;


import com.google.gson.annotations.SerializedName;


public class Data{

	@SerializedName("id")
	private double id;

	@SerializedName("type")
	private String type;

	public void setId(double id){
		this.id = id;
	}

	public double getId(){
		return id;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"id = '" + id + '\'' + 
			",type = '" + type + '\'' + 
			"}";
		}
}