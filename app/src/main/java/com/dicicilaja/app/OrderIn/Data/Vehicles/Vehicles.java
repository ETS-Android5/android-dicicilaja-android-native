package com.dicicilaja.app.OrderIn.Data.Vehicles;

import com.google.gson.annotations.SerializedName;

public class Vehicles{

	@SerializedName("vehicleName")
	private String vehicleName;

	@SerializedName("nama")
	private String nama;

	@SerializedName("vehicleCode")
	private String vehicleCode;

	@SerializedName("tipeObjekId")
	private String tipeObjekId;

	@SerializedName("id")
	private int id;

	@SerializedName("vehicleType")
	private String vehicleType;

	public void setVehicleName(String vehicleName){
		this.vehicleName = vehicleName;
	}

	public String getVehicleName(){
		return vehicleName;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setVehicleCode(String vehicleCode){
		this.vehicleCode = vehicleCode;
	}

	public String getVehicleCode(){
		return vehicleCode;
	}

	public void setTipeObjekId(String tipeObjekId){
		this.tipeObjekId = tipeObjekId;
	}

	public String getTipeObjekId(){
		return tipeObjekId;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setVehicleType(String vehicleType){
		this.vehicleType = vehicleType;
	}

	public String getVehicleType(){
		return vehicleType;
	}
}