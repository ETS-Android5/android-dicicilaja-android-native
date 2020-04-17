package com.dicicilaja.app.OrderIn.Data.Kelurahan;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Branches{

	@SerializedName("data")
	private List<Object> data;

	@SerializedName("links")
	private Links links;

	public void setData(List<Object> data){
		this.data = data;
	}

	public List<Object> getData(){
		return data;
	}

	public void setLinks(Links links){
		this.links = links;
	}

	public Links getLinks(){
		return links;
	}
}