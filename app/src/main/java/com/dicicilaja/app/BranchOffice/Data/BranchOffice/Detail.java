package com.dicicilaja.app.BranchOffice.Data.BranchOffice;

import com.google.gson.annotations.SerializedName;

public class Detail{

	@SerializedName("provinsi")
	private String provinsi;

	@SerializedName("kota")
	private String kota;

	@SerializedName("telp_1")
	private String telp1;

	@SerializedName("fax_1")
	private String fax1;

	@SerializedName("kecamatan")
	private String kecamatan;

	@SerializedName("kode_pos")
	private String kodePos;

	@SerializedName("fax_3")
	private Object fax3;

	@SerializedName("fax_2")
	private Object fax2;

	@SerializedName("telp_2")
	private String telp2;

	@SerializedName("telp_3")
	private Object telp3;

	@SerializedName("kelurahan")
	private String kelurahan;

	@SerializedName("alamat")
	private String alamat;

	public void setProvinsi(String provinsi){
		this.provinsi = provinsi;
	}

	public String getProvinsi(){
		return provinsi;
	}

	public void setKota(String kota){
		this.kota = kota;
	}

	public String getKota(){
		return kota;
	}

	public void setTelp1(String telp1){
		this.telp1 = telp1;
	}

	public String getTelp1(){
		return telp1;
	}

	public void setFax1(String fax1){
		this.fax1 = fax1;
	}

	public String getFax1(){
		return fax1;
	}

	public void setKecamatan(String kecamatan){
		this.kecamatan = kecamatan;
	}

	public String getKecamatan(){
		return kecamatan;
	}

	public void setKodePos(String kodePos){
		this.kodePos = kodePos;
	}

	public String getKodePos(){
		return kodePos;
	}

	public void setFax3(Object fax3){
		this.fax3 = fax3;
	}

	public Object getFax3(){
		return fax3;
	}

	public void setFax2(Object fax2){
		this.fax2 = fax2;
	}

	public Object getFax2(){
		return fax2;
	}

	public void setTelp2(String telp2){
		this.telp2 = telp2;
	}

	public String getTelp2(){
		return telp2;
	}

	public void setTelp3(Object telp3){
		this.telp3 = telp3;
	}

	public Object getTelp3(){
		return telp3;
	}

	public void setKelurahan(String kelurahan){
		this.kelurahan = kelurahan;
	}

	public String getKelurahan(){
		return kelurahan;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}
}