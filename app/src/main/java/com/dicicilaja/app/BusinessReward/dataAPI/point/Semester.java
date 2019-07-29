package com.dicicilaja.app.BusinessReward.dataAPI.point;

import com.google.gson.annotations.SerializedName;

public class Semester{

	@SerializedName("data")
	private Data data;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}
}