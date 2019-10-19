package com.dicicilaja.app.OrderIn.Data.Kecamatan;

import com.google.gson.annotations.SerializedName;

public class Relationships{

	@SerializedName("villages")
	private Villages villages;

	@SerializedName("city")
	private City city;

	public void setVillages(Villages villages){
		this.villages = villages;
	}

	public Villages getVillages(){
		return villages;
	}

	public void setCity(City city){
		this.city = city;
	}

	public City getCity(){
		return city;
	}
}