package com.dicicilaja.app.NewSimulation.Data.ObjekBrand;

import com.google.gson.annotations.SerializedName;

public class Attributes{

	@SerializedName("tipe_objek")
	private String tipeObjek;

	@SerializedName("nama")
	private String nama;

	public void setTipeObjek(String tipeObjek){
		this.tipeObjek = tipeObjek;
	}

	public String getTipeObjek(){
		return tipeObjek;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}
}