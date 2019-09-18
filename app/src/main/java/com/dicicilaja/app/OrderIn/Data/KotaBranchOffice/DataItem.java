package com.dicicilaja.app.OrderIn.Data.KotaBranchOffice;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("attributes")
	private Attributes attributes;

	@SerializedName("type")
	private String type;

	public void setAttributes(Attributes attributes){
		this.attributes = attributes;
	}

	public Attributes getAttributes(){
		return attributes;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}
}