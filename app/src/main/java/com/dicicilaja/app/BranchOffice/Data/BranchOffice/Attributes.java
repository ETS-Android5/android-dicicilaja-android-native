package com.dicicilaja.app.BranchOffice.Data.BranchOffice;

import com.google.gson.annotations.SerializedName;

public class Attributes{

	@SerializedName("nama")
	private String nama;

	@SerializedName("kode_cabang")
	private String kodeCabang;

	@SerializedName("lokasi")
	private String lokasi;

	@SerializedName("detail")
	private Detail detail;

	@SerializedName("region")
	private String region;

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setKodeCabang(String kodeCabang){
		this.kodeCabang = kodeCabang;
	}

	public String getKodeCabang(){
		return kodeCabang;
	}

	public void setLokasi(String lokasi){
		this.lokasi = lokasi;
	}

	public String getLokasi(){
		return lokasi;
	}

	public void setDetail(Detail detail){
		this.detail = detail;
	}

	public Detail getDetail(){
		return detail;
	}

	public void setRegion(String region){
		this.region = region;
	}

	public String getRegion(){
		return region;
	}
}