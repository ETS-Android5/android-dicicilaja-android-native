package com.dicicilaja.app.OrderIn.Data.AreaBranchOffice;

import com.google.gson.annotations.SerializedName;

public class Attributes{

	@SerializedName("region")
	private String region;

	public void setRegion(String region){
		this.region = region;
	}

	public String getRegion(){
		return region;
	}
}