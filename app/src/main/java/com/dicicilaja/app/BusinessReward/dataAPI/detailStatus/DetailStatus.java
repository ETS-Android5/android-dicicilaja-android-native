package com.dicicilaja.app.BusinessReward.dataAPI.detailStatus;

import com.google.gson.annotations.SerializedName;

public class DetailStatus{

	@SerializedName("data")
	private Data data;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}
}