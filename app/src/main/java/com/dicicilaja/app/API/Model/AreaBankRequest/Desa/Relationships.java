package com.dicicilaja.app.API.Model.AreaBankRequest.Desa;


import com.google.gson.annotations.SerializedName;


public class Relationships{

	@SerializedName("distrik")
	private Distrik distrik;

	public void setDistrik(Distrik distrik){
		this.distrik = distrik;
	}

	public Distrik getDistrik(){
		return distrik;
	}

	@Override
 	public String toString(){
		return 
			"Relationships{" + 
			"distrik = '" + distrik + '\'' + 
			"}";
		}
}