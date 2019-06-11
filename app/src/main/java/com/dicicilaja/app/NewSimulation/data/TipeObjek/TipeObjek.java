package com.dicicilaja.app.NewSimulation.data.TipeObjek;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TipeObjek{

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("meta")
	private Meta meta;

	@SerializedName("links")
	private Links links;

	public TipeObjek(List<DataItem> data, Meta meta, Links links) {
		this.data = data;
		this.meta = meta;
		this.links = links;
	}

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
}