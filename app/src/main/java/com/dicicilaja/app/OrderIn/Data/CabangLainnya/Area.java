package com.dicicilaja.app.OrderIn.Data.CabangLainnya;

import com.google.gson.annotations.SerializedName;

public class Area{

	@SerializedName("data")
	private Data data;

	@SerializedName("links")
	private Links links;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setLinks(Links links){
		this.links = links;
	}

	public Links getLinks(){
		return links;
	}
}