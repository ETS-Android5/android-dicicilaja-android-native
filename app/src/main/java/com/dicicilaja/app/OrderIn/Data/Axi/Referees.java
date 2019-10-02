package com.dicicilaja.app.OrderIn.Data.Axi;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Referees{

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("links")
	private Links links;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setLinks(Links links){
		this.links = links;
	}

	public Links getLinks(){
		return links;
	}
}