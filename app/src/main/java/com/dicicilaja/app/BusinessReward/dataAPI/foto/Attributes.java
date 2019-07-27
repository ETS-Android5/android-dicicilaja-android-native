package com.dicicilaja.app.BusinessReward.dataAPI.foto;

import com.google.gson.annotations.SerializedName;

public class Attributes{

	@SerializedName("foto_npwp")
	private String fotoNpwp;

	@SerializedName("no_ktp")
	private String noKtp;

	@SerializedName("axi_id")
	private String axiId;

	@SerializedName("foto_ktp")
	private String fotoKtp;

	@SerializedName("no_npwp")
	private String noNpwp;

	public void setFotoNpwp(String fotoNpwp){
		this.fotoNpwp = fotoNpwp;
	}

	public String getFotoNpwp(){
		return fotoNpwp;
	}

	public void setNoKtp(String noKtp){
		this.noKtp = noKtp;
	}

	public String getNoKtp(){
		return noKtp;
	}

	public void setAxiId(String axiId){
		this.axiId = axiId;
	}

	public String getAxiId(){
		return axiId;
	}

	public void setFotoKtp(String fotoKtp){
		this.fotoKtp = fotoKtp;
	}

	public String getFotoKtp(){
		return fotoKtp;
	}

	public void setNoNpwp(String noNpwp){
		this.noNpwp = noNpwp;
	}

	public String getNoNpwp(){
		return noNpwp;
	}
}