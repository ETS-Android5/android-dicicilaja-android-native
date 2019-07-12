package com.dicicilaja.app.NewSimulation.Data.HitungSimulasi;

import com.google.gson.annotations.SerializedName;

public class HasilSimulasi{

	@SerializedName("maks_pencairan")
	private int maksPencairan;

	@SerializedName("dana_diterima_prefix")
	private String danaDiterimaPrefix;

	@SerializedName("dana_diterima")
	private int danaDiterima;

	@SerializedName("maks_pencairan_prefix")
	private String maksPencairanPrefix;

	@SerializedName("angsuran_per_bulan")
	private String angsuranPerBulan;

	@SerializedName("angsuran_per_bulan_prefix")
	private String angsuranPerBulanPrefix;

	public String getAngsuranPerBulanPrefix() {
		return angsuranPerBulanPrefix;
	}

	public void setAngsuranPerBulanPrefix(String angsuranPerBulanPrefix) {
		this.angsuranPerBulanPrefix = angsuranPerBulanPrefix;
	}

	public void setMaksPencairan(int maksPencairan){
		this.maksPencairan = maksPencairan;
	}

	public int getMaksPencairan(){
		return maksPencairan;
	}

	public void setDanaDiterimaPrefix(String danaDiterimaPrefix){
		this.danaDiterimaPrefix = danaDiterimaPrefix;
	}

	public String getDanaDiterimaPrefix(){
		return danaDiterimaPrefix;
	}

	public void setDanaDiterima(int danaDiterima){
		this.danaDiterima = danaDiterima;
	}

	public int getDanaDiterima(){
		return danaDiterima;
	}

	public void setMaksPencairanPrefix(String maksPencairanPrefix){
		this.maksPencairanPrefix = maksPencairanPrefix;
	}

	public String getMaksPencairanPrefix(){
		return maksPencairanPrefix;
	}

	public void setAngsuranPerBulan(String angsuranPerBulan){
		this.angsuranPerBulan = angsuranPerBulan;
	}

	public String getAngsuranPerBulan(){
		return angsuranPerBulan;
	}
}