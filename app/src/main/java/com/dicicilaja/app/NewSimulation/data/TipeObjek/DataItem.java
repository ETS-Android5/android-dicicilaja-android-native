package com.dicicilaja.app.NewSimulation.data.TipeObjek;

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

	public DataItem(Attributes attributes, Links links, int id, String type) {
		this.attributes = attributes;
		this.links = links;
		this.id = id;
		this.type = type;
	}


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