package com.dicicilaja.app.OrderIn.Data.AreaSimulasi;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AreaSimulasi{

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("meta")
	private Meta meta;

	@SerializedName("links")
	private Links links;

	@SerializedName("included")
	private List<IncludedItem> included;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setMeta(Meta meta){
		this.meta = meta;
	}

	public Meta getMeta(){
		return meta;
	}

	public void setLinks(Links links){
		this.links = links;
	}

	public Links getLinks(){
		return links;
	}

	public void setIncluded(List<IncludedItem> included){
		this.included = included;
	}

	public List<IncludedItem> getIncluded(){
		return included;
	}
}