package com.dicicilaja.app.Activity.BusinessReward.dataAPI.produk;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Produk{

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("meta")
	private Meta meta;

	@SerializedName("links")
	private Links links;

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