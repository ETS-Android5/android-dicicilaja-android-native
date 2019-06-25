package com.dicicilaja.app.Activity.BusinessReward.dataAPI.detailClaimReward;

import com.google.gson.annotations.SerializedName;

public class DetailClaimReward{

	@SerializedName("data")
	private Data data;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}
}