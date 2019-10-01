package com.dicicilaja.app.OrderIn.Data.AreaSimulasi;

import com.google.gson.annotations.SerializedName;

public class IncludedItem{

	@SerializedName("attributes")
	private Attributes attributes;

	@SerializedName("id")
	private int id;

	@SerializedName("type")
	private String type;

	public void setAttributes(Attributes attributes){
		this.attributes = attributes;
	}

	public Attributes getAttributes(){
		return attributes;
	}

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
}