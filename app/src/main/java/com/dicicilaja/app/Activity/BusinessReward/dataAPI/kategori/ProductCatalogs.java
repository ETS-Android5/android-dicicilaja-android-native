package com.dicicilaja.app.Activity.BusinessReward.dataAPI.kategori;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductCatalogs{

	@SerializedName("data")
	@Expose
	private List<Datum_> data = null;

	public List<Datum_> getData() {
		return data;
	}

	public void setData(List<Datum_> data) {
		this.data = data;
	}
}