package com.dicicilaja.app.Activity.BusinessReward.dataAPI.kategori;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes {

	@SerializedName("nama")
	@Expose
	private String nama;

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

}