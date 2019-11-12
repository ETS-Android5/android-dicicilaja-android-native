package com.dicicilaja.app.Inbox.Data.Notif;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Notif{

	@SerializedName("data")
	private List<DataItem> data;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}
}