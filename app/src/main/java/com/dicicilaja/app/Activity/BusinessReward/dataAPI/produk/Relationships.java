package com.dicicilaja.app.Activity.BusinessReward.dataAPI.produk;

import com.google.gson.annotations.SerializedName;

public class Relationships{

	@SerializedName("status")
	private Status status;

	public void setStatus(Status status){
		this.status = status;
	}

	public Status getStatus(){
		return status;
	}
}