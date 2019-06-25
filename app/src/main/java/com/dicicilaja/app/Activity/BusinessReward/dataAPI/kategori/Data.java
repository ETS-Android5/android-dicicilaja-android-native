package com.dicicilaja.app.Activity.BusinessReward.dataAPI.kategori;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("type")
	@Expose
	private String type;
	@SerializedName("id")
	@Expose
	private Integer id;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}