package com.dicicilaja.app.Inbox.Data.Popup;

import com.google.gson.annotations.SerializedName;

public class Popup{

	@SerializedName("data")
	private Data data;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}
}