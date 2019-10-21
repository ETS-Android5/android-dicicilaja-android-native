package com.dicicilaja.app.OrderIn.Data.VoucherCode;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class VoucherCode{

	@SerializedName("data")
	private List<DataItem> data;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}
}