package com.dicicilaja.app.NewSimulation.data.objekbrand;

import com.google.gson.annotations.SerializedName;

public class Links{

	@SerializedName("self")
	private String self;

	public void setSelf(String self){
		this.self = self;
	}

	public String getSelf(){
		return self;
	}
}