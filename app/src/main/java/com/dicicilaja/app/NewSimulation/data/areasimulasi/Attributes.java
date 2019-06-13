package com.dicicilaja.app.NewSimulation.data.areasimulasi;

import com.google.gson.annotations.SerializedName;

public class Attributes{

	@SerializedName("wilayah_id")
	private int wilayahId;

	@SerializedName("nama")
	private String nama;

	@SerializedName("is_area_simulasi")
	private boolean isAreaSimulasi;

	public void setWilayahId(int wilayahId){
		this.wilayahId = wilayahId;
	}

	public int getWilayahId(){
		return wilayahId;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setIsAreaSimulasi(boolean isAreaSimulasi){
		this.isAreaSimulasi = isAreaSimulasi;
	}

	public boolean isIsAreaSimulasi(){
		return isAreaSimulasi;
	}
}