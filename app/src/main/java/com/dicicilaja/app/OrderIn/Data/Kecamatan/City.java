package com.dicicilaja.app.OrderIn.Data.Kecamatan;

import com.google.gson.annotations.SerializedName;

public class City{

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