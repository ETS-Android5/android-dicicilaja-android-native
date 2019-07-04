package com.dicicilaja.app.BusinessReward.dataAPI.testimoni;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Testimoni{

	@SerializedName("data")
	@Expose
	private Data data;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
}