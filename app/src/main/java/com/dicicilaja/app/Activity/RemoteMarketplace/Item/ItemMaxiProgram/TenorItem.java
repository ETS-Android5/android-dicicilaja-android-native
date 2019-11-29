package com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemMaxiProgram;

import com.google.gson.annotations.SerializedName;

public class TenorItem{

	@SerializedName("cicilan")
	private String cicilan;

	@SerializedName("bulan")
	private String bulan;

	public void setCicilan(String cicilan){
		this.cicilan = cicilan;
	}

	public String getCicilan(){
		return cicilan;
	}

	public void setBulan(String bulan){
		this.bulan = bulan;
	}

	public String getBulan(){
		return bulan;
	}
}