package com.dicicilaja.app.NewSimulation.data.hitungsimulasi;

import com.google.gson.annotations.SerializedName;

public class InformasiJaminan{

	@SerializedName("area")
	private String area;

	@SerializedName("kendaraan")
	private String kendaraan;

	@SerializedName("tenor")
	private int tenor;

	@SerializedName("tipe_asuransi")
	private String tipeAsuransi;

	@SerializedName("tahun_kendaraan")
	private String tahunKendaraan;

	@SerializedName("type_kendaraan")
	private String typeKendaraan;

	@SerializedName("merk_kendaraan")
	private String merkKendaraan;

	@SerializedName("tipe_angsuran")
	private String tipeAngsuran;

	public void setArea(String area){
		this.area = area;
	}

	public String getArea(){
		return area;
	}

	public void setKendaraan(String kendaraan){
		this.kendaraan = kendaraan;
	}

	public String getKendaraan(){
		return kendaraan;
	}

	public void setTenor(int tenor){
		this.tenor = tenor;
	}

	public int getTenor(){
		return tenor;
	}

	public void setTipeAsuransi(String tipeAsuransi){
		this.tipeAsuransi = tipeAsuransi;
	}

	public String getTipeAsuransi(){
		return tipeAsuransi;
	}

	public void setTahunKendaraan(String tahunKendaraan){
		this.tahunKendaraan = tahunKendaraan;
	}

	public String getTahunKendaraan(){
		return tahunKendaraan;
	}

	public void setTypeKendaraan(String typeKendaraan){
		this.typeKendaraan = typeKendaraan;
	}

	public String getTypeKendaraan(){
		return typeKendaraan;
	}

	public void setMerkKendaraan(String merkKendaraan){
		this.merkKendaraan = merkKendaraan;
	}

	public String getMerkKendaraan(){
		return merkKendaraan;
	}

	public void setTipeAngsuran(String tipeAngsuran){
		this.tipeAngsuran = tipeAngsuran;
	}

	public String getTipeAngsuran(){
		return tipeAngsuran;
	}
}