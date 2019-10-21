package com.dicicilaja.app.OrderIn.Data.Kelurahan;

import com.google.gson.annotations.SerializedName;

public class Relationships{

	@SerializedName("district")
	private District district;

	@SerializedName("branches")
	private Branches branches;

	public void setDistrict(District district){
		this.district = district;
	}

	public District getDistrict(){
		return district;
	}

	public void setBranches(Branches branches){
		this.branches = branches;
	}

	public Branches getBranches(){
		return branches;
	}
}