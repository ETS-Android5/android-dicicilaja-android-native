package com.dicicilaja.app.NewSimulation.Data.ObjekTahun;

import com.google.gson.annotations.SerializedName;

public class Attributes{

	@SerializedName("area_id")
	private String areaId;

	@SerializedName("objek_model_id")
	private String objekModelId;

	public void setAreaId(String areaId){
		this.areaId = areaId;
	}

	public String getAreaId(){
		return areaId;
	}

	public void setObjekModelId(String objekModelId){
		this.objekModelId = objekModelId;
	}

	public String getObjekModelId(){
		return objekModelId;
	}

	public Attributes(String areaId, String objekModelId) {
		this.areaId = areaId;
		this.objekModelId = objekModelId;
	}
}