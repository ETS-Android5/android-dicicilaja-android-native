package com.dicicilaja.app.Activity.BusinessReward.dataAPI.detailTestimoni;

import com.google.gson.annotations.SerializedName;

public class DetailTestimoni{

	@SerializedName("data")
	private Data data;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}
}