package com.dicicilaja.app.BusinessReward.dataAPI.foto;

import com.google.gson.annotations.SerializedName;

public class Foto{

	@SerializedName("data")
	private Data data;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}
}