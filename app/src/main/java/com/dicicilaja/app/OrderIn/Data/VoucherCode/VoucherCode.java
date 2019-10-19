package com.dicicilaja.app.OrderIn.Data.VoucherCode;

import com.google.gson.annotations.SerializedName;

public class VoucherCode{

	@SerializedName("data")
	private Data data;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}
}