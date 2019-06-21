package com.dicicilaja.app.NewSimulation.Data.ObjekModel;

import com.google.gson.annotations.SerializedName;

public class Attributes{

	@SerializedName("tipe_objek")
	private String tipeObjek;

	@SerializedName("objek_brand")
	private String objekBrand;

	@SerializedName("kode_model")
	private String kodeModel;

	@SerializedName("tipe_kendaraan")
	private String tipeKendaraan;

	@SerializedName("nama_objek")
	private String namaObjek;

	@SerializedName("group")
	private String group;

	public void setTipeObjek(String tipeObjek){
		this.tipeObjek = tipeObjek;
	}

	public String getTipeObjek(){
		return tipeObjek;
	}

	public void setObjekBrand(String objekBrand){
		this.objekBrand = objekBrand;
	}

	public String getObjekBrand(){
		return objekBrand;
	}

	public void setKodeModel(String kodeModel){
		this.kodeModel = kodeModel;
	}

	public String getKodeModel(){
		return kodeModel;
	}

	public void setTipeKendaraan(String tipeKendaraan){
		this.tipeKendaraan = tipeKendaraan;
	}

	public String getTipeKendaraan(){
		return tipeKendaraan;
	}

	public void setNamaObjek(String namaObjek){
		this.namaObjek = namaObjek;
	}

	public String getNamaObjek(){
		return namaObjek;
	}

	public void setGroup(String group){
		this.group = group;
	}

	public String getGroup(){
		return group;
	}
}