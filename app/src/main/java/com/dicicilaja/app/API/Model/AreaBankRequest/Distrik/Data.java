package com.dicicilaja.app.API.Model.AreaBankRequest.Distrik;


import com.google.gson.annotations.SerializedName;


public class Data{

	@SerializedName("id")
	private int id;

	@SerializedName("type")
	private String type;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
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