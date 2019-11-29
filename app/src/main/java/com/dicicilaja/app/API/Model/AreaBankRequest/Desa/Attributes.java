package com.dicicilaja.app.API.Model.AreaBankRequest.Desa;


import com.google.gson.annotations.SerializedName;


public class Attributes{

	@SerializedName("nama")
	private String nama;

	@SerializedName("kode_pos")
	private String kodePos;

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setKodePos(String kodePos){
		this.kodePos = kodePos;
	}

	public String getKodePos(){
		return kodePos;
	}

	@Override
 	public String toString(){
		return 
			"Attributes{" + 
			"nama = '" + nama + '\'' + 
			",kode_pos = '" + kodePos + '\'' + 
			"}";
		}
}