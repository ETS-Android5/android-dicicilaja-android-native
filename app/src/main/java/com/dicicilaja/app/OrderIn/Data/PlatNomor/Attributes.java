package com.dicicilaja.app.OrderIn.Data.PlatNomor;

import com.google.gson.annotations.SerializedName;

public class Attributes{

	@SerializedName("amount")
	private int amount;

	@SerializedName("cabang_id")
	private int cabangId;

	@SerializedName("calon_nasabah_id")
	private int calonNasabahId;

	@SerializedName("agen_id")
	private int agenId;

	@SerializedName("pk_number")
	private int pkNumber;

	@SerializedName("final_amount")
	private int finalAmount;

	@SerializedName("area_id")
	private int areaId;

	@SerializedName("subproduk_id")
	private int subprodukId;

	@SerializedName("vehicle_id")
	private String vehicleId;

	@SerializedName("current_pic")
	private int currentPic;

	@SerializedName("status")
	private int status;

	public void setAmount(int amount){
		this.amount = amount;
	}

	public int getAmount(){
		return amount;
	}

	public void setCabangId(int cabangId){
		this.cabangId = cabangId;
	}

	public int getCabangId(){
		return cabangId;
	}

	public void setCalonNasabahId(int calonNasabahId){
		this.calonNasabahId = calonNasabahId;
	}

	public int getCalonNasabahId(){
		return calonNasabahId;
	}

	public void setAgenId(int agenId){
		this.agenId = agenId;
	}

	public int getAgenId(){
		return agenId;
	}

	public void setPkNumber(int pkNumber){
		this.pkNumber = pkNumber;
	}

	public int getPkNumber(){
		return pkNumber;
	}

	public void setFinalAmount(int finalAmount){
		this.finalAmount = finalAmount;
	}

	public int getFinalAmount(){
		return finalAmount;
	}

	public void setAreaId(int areaId){
		this.areaId = areaId;
	}

	public int getAreaId(){
		return areaId;
	}

	public void setSubprodukId(int subprodukId){
		this.subprodukId = subprodukId;
	}

	public int getSubprodukId(){
		return subprodukId;
	}

	public void setVehicleId(String vehicleId){
		this.vehicleId = vehicleId;
	}

	public String getVehicleId(){
		return vehicleId;
	}

	public void setCurrentPic(int currentPic){
		this.currentPic = currentPic;
	}

	public int getCurrentPic(){
		return currentPic;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}
}