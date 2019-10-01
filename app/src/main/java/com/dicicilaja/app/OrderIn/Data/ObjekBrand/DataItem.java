package com.dicicilaja.app.OrderIn.Data.ObjekBrand;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("attributes")
	private Attributes attributes;

	@SerializedName("links")
	private Links links;

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

	public void setLinks(Links links){
		this.links = links;
	}

	public Links getLinks(){
		return links;
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