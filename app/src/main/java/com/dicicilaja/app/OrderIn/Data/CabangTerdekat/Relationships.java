package com.dicicilaja.app.OrderIn.Data.CabangTerdekat;

import com.google.gson.annotations.SerializedName;

public class Relationships{

	@SerializedName("area")
	private Area area;

	@SerializedName("village")
	private Village village;

	public void setArea(Area area){
		this.area = area;
	}

	public Area getArea(){
		return area;
	}

	public void setVillage(Village village){
		this.village = village;
	}

	public Village getVillage(){
		return village;
	}
}