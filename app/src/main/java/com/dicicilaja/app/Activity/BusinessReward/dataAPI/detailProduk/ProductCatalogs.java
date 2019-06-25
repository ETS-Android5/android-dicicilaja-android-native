package com.dicicilaja.app.Activity.BusinessReward.dataAPI.detailProduk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductCatalogs{

	@SerializedName("data")
	@Expose
	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}