package com.dicicilaja.app.OrderIn.Data.TahunKendaraan;

import com.google.gson.annotations.SerializedName;

public class Attributes{

	@SerializedName("tahun")
	private String tahun;

	public void setTahun(String tahun){
		this.tahun = tahun;
	}

	public String getTahun(){
		return tahun;
	}
}