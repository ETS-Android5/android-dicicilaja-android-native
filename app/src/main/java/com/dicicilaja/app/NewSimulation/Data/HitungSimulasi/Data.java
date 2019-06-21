package com.dicicilaja.app.NewSimulation.Data.HitungSimulasi;

import com.google.gson.annotations.SerializedName;

public class Data{

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